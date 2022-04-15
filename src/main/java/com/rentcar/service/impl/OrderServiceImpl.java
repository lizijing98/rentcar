package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.CarInfo;
import com.rentcar.bean.Check;
import com.rentcar.bean.Customer;
import com.rentcar.bean.Order;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.OrderMapper;
import com.rentcar.service.CarInfoService;
import com.rentcar.service.CheckService;
import com.rentcar.service.CustomerService;
import com.rentcar.service.OrderService;
import com.rentcar.util.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-19
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private CustomerService customerService;
    private CarInfoService carInfoService;
    private OrderUtils orderUtils;
    private CheckService checkService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initOrder(Serializable carInfoId, Serializable customerId, Integer day) {
        log.info("carInfoId:{},customerId:{},day:{}", carInfoId, customerId, day);
        Customer customer = customerService.getById(customerId);
        CarInfo carInfo = carInfoService.getById(carInfoId);
        log.info("carInfo:{}, customer:{}", carInfo, customer);
        // 客户的余额小于押金
        if (customer.getMoney().compareTo(carInfo.getMoney()) <= 0) {
            throw new BusinessException("余额不足，不能租赁");
        }
        if ("已出租".equals(carInfo.getStatus())) {
            throw new BusinessException("该汽车已经被出租");
        }
        long now = System.currentTimeMillis();
        long end = now + day * 24 * 60 * 60 * 1000;
        Date startDate = new Date(now);
        Date endDate = new Date(end);
        Order order = new Order();
        order.setCarInfoId(carInfo.getId());
        order.setCustomerId(customer.getId());
        order.setTenancyTerm(day);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setCashPledge(carInfo.getCashPledge());
        order.setMoney(carInfo.getMoney());
        order.setTenancyTerm(day);
        order.setState(1);
        // 生成订单编号
        order.setOrderNumber(orderUtils.getOrderCode(customer.getId()));
        int i = getBaseMapper().insert(order);
        if (i > 0) {
            carInfo.setStatus("已出租");
            carInfoService.updateById(carInfo);
        }
        return i > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateState(Integer id, Integer state, String feedback) {
        log.info("处理订单状态 订单id：{}，状态：{}", id, state);
        Order order = getBaseMapper().selectById(id);
        order.setFeedback(feedback);
        if (state == 4){
            if (order.getState().equals(2)){
                order.setState(state);
                if (!this.updateById(order)){
                    throw new BusinessException("申请还车失败!");
                }
            } else if (order.getState().equals(5)) {
                order.setState(state);
                if (!this.updateById(order)){
                    throw new BusinessException("重新申请还车失败!");
                }
            } else {
                throw new BusinessException("其他异常!");
            }
        }
        // 借车申请处理
        if (state == 2 || state == 3) {
            if (order.getState().equals(1)) {
                if (state == 2) {
                    order.setOutDate(new Date());
                }
                order.setState(state);
                if (!this.updateById(order)){
                    throw new BusinessException("订单处理失败！请稍后重试。");
                }
            } else if (order.getState().equals(4)) {
                order.setState(state);
                if (!this.updateById(order)) {
                    throw new BusinessException("取消申请失败!");
                }
            } else {
                throw new BusinessException("其他异常!");
            }
        }

        // 拒绝还车
        if (state == 5) {
            if (order.getState().equals(4)) {
                order.setState(state);
                this.updateById(order);
            } else {
                throw new BusinessException("订单处理失败！请稍后重试。");
            }
        }
        // 同意还车
        if (state == 6) {
            if (order.getState().equals(4)) {
                log.info("还车开始---");
                order.setState(state);
                order.setInDate(new Date());
                Date endDate = order.getEndDate();
                // 结束日期在当前时间之后
                BigDecimal money = order.getMoney();
                // 正常租金
                BigDecimal zj = BigDecimal.valueOf(order.getTenancyTerm()).multiply(money);
                BigDecimal fj = BigDecimal.ZERO;
                double day = 0;
                if (endDate.compareTo(new Date()) < 0) {
                    // 不满一天按一天算
                    day = (System.currentTimeMillis()*1.0 - endDate.getTime()*1.0) / (1000.0 * 60 * 60.0 * 24.0)+1;
                    // 2倍的日租金罚金
                    fj = money.multiply(BigDecimal.valueOf(day)).multiply(BigDecimal.valueOf(2));
                }
                QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("order_id", order.getId());
                queryWrapper.orderByDesc("create_time");
                final Check check = checkService.list(queryWrapper).get(0);
                BigDecimal pf = check.getMoney();
                BigDecimal result = zj.add(fj).add(pf);
                log.info("日租金{},租期:{},逾期:{},正常租金:{},罚金:{},赔付金额:{},总金额：{}",
                        money, order.getTenancyTerm(), day, zj, fj, pf, result);
                Customer customer = customerService.getById(order.getCustomerId());
                // 减去租金
                customer.setMoney(customer.getMoney().subtract(result));
                customerService.updateById(customer);
                this.updateById(order);
            } else {
                throw new BusinessException("订单处理失败！请稍后重试。");
            }
        }
        // 取消订单
        if (state == 7) {
            if (order.getState().equals(1)) {
                order.setState(state);
                this.updateById(order);
            } else {
                throw new BusinessException("订单状态已改变，不能取消！");
            }

        }
        // 修改汽车状态
        if (state == 6 || state == 7 || state == 3) {
            Integer carInfoId = order.getCarInfoId();
            CarInfo carInfo = new CarInfo();
            carInfo.setId(carInfoId).setStatus("未出租");
            carInfoService.updateById(carInfo);
        }
        return true;
    }

    @Autowired
    public void setCarInfoService(CarInfoService carInfoService) {
        this.carInfoService = carInfoService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setOrderUtils(OrderUtils orderUtils) {
        this.orderUtils = orderUtils;
    }

    @Autowired
    public void setCheckService(CheckService checkService) {
        this.checkService = checkService;
    }
}