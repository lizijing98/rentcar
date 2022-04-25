package com.rentcar.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.CarInfo;
import com.rentcar.bean.Check;
import com.rentcar.bean.Customer;
import com.rentcar.bean.Order;
import com.rentcar.bean.VO.InitOrderVO;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.OrderMapper;
import com.rentcar.service.*;
import com.rentcar.util.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
  @Resource private OrderMapper orderMapper;
  @Resource private AssessService assessService;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean initOrder(InitOrderVO initOrderVO, Serializable customerId) {
    log.info("******初始化订单******");
    log.info("orderInfo:{},customerId:{}", initOrderVO.toString(), customerId);
    Customer customer = customerService.getById(customerId);
    CarInfo carInfo = carInfoService.getById(initOrderVO.getCarInfoId());
    log.info("carInfo:{}, customer:{}", carInfo, customer);
    // 客户的余额小于押金
    if (customer.getMoney().compareTo(carInfo.getMoney()) <= 0) {
      throw new BusinessException("余额不足，不能租赁");
    }
    if ("已出租".equals(carInfo.getStatus())) {
      throw new BusinessException("该汽车已经被出租");
    }
    Date startDate = DateUtil.parse(initOrderVO.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
    Date endDate = DateUtil.parse(initOrderVO.getFinishDate(), "yyyy-MM-dd HH:mm:ss");
    Double day = DateUtil.between(startDate, endDate, DateUnit.HOUR) / 24.0;
    BigDecimal money = NumberUtil.mul(day, carInfo.getMoney());
    Order order =
        new Order()
            .setCarInfoId(carInfo.getId())
            .setCustomerId(customer.getId())
            .setStartDate(LocalDateTimeUtil.of(startDate))
            .setEndDate(LocalDateTimeUtil.of(endDate))
            .setCashPledge(carInfo.getCashPledge())
            .setMoney(money)
            .setTenancyTerm(day)
            .setCustomerIdNum(initOrderVO.getIdNumber())
            .setState(1);
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
    if (state == 4) {
      if (order.getState().equals(2)) {
        order.setState(state);
        if (!this.updateById(order)) {
          throw new BusinessException("申请还车失败!");
        }
      } else if (order.getState().equals(5)) {
        order.setState(state);
        if (!this.updateById(order)) {
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
          order.setOutDate(LocalDateTime.now());
        }
        order.setState(state);
        if (!this.updateById(order)) {
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
      if (order.getState().equals(4) || order.getState().equals(7)) {
        log.info("还车开始---");
        order.setState(state);
        order.setInDate(LocalDateTime.now());
        LocalDateTime endDate = order.getEndDate();
        // 结束日期在当前时间之后
        BigDecimal money = order.getMoney();
        // 正常租金
        BigDecimal zj = BigDecimal.valueOf(order.getTenancyTerm()).multiply(money);
        BigDecimal fj = BigDecimal.ZERO;
        double day = 0;
        if (endDate.compareTo(LocalDateTime.now()) < 0) {
          // 不满一天按一天算
          day =
              (System.currentTimeMillis() * 1.0 - Timestamp.valueOf(endDate).getTime())
                      / (1000.0 * 60 * 60.0 * 24.0)
                  + 1;
          // 2倍的日租金罚金
          fj = money.multiply(BigDecimal.valueOf(day)).multiply(BigDecimal.valueOf(2));
        }
        QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", order.getId());
        queryWrapper.orderByDesc("create_time");
        final Check check = checkService.list(queryWrapper).get(0);
        BigDecimal pf = check.getMoney();
        BigDecimal result = zj.add(fj).add(pf);
        result = result.setScale(0, BigDecimal.ROUND_UP);
        log.info(
            "订单 ID:{},日租金:{},租期:{},逾期:{},正常租金:{},罚金:{},赔付金额:{},总金额：{}",
            id,
            money,
            order.getTenancyTerm(),
            day,
            zj,
            fj,
            pf,
            result);
        Customer customer = customerService.getById(order.getCustomerId());
        // 减去租金
        customer.setMoney(customer.getMoney().subtract(result));
        customerService.updateById(customer);
        this.updateById(order);
        // 保存评价
        //        Assess assess = BeanUtil.copyProperties(order, Assess.class, "id,fversion,state");
        //		assess.setState(0);
        //        assess.setRemark(check.getRemark());
        assessService.initAssess(order.getOrderNumber(), order.getCustomerId(), check.getRemark());
        //        assessMapper.insert(assess);
      } else {
        throw new BusinessException("订单处理失败！请稍后重试。");
      }
    }
    // 提交事故
    if (state == 7) {
      if (order.getState().equals(2)) {
        order.setState(state);
        this.updateById(order);
      } else {
        throw new BusinessException("订单状态异常，无法创建事故单");
      }
    }

    // 取消订单
    if (state == 8) {
      if (order.getState().equals(1)) {
        order.setState(state);
        this.updateById(order);
      } else {
        throw new BusinessException("订单状态已改变，不能取消！");
      }
    }
    // 修改汽车状态
    if (state == 6 || state == 8 || state == 3) {
      Integer carInfoId = order.getCarInfoId();
      CarInfo carInfo = new CarInfo();
      carInfo.setId(carInfoId).setStatus("未出租");
      carInfoService.updateById(carInfo);
    } else if (state == 7) {
      CarInfo carInfo = new CarInfo();
      carInfo.setId(order.getCarInfoId()).setStatus("事故处理中");
      carInfoService.updateById(carInfo);
    }
    return true;
  }

  @Override
  public Order getOneByOrderNum(String orderNum) {
    return orderMapper.getOneByOrderNum(orderNum);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean updateMoney(Integer id, BigDecimal money) {
    log.info("修改订单金额: 订单 ID {}, 增加金额 {}", id, money);
    Order order = this.getById(id);
    order.setMoney(order.getMoney().add(money));
    return this.updateById(order);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean delay(Integer id, Integer day) {
    log.info("延长订单时间: ");
    Order order = this.getById(id);
    if (order.getState() != 2) {
      throw new BusinessException("订单状态不正确，无法延期");
    }
    // 修改租期
    order.setTenancyTerm(order.getTenancyTerm() + day);
    // 修改结束时间
    LocalDateTime oldEndData = order.getEndDate();
    LocalDateTime newEndData = LocalDateTimeUtil.offset(oldEndData, day, ChronoUnit.DAYS);
    order.setEndDate(newEndData);
    // 修改订单金额
    BigDecimal carMoney = carInfoService.getMoneyById(order.getCarInfoId());
    BigDecimal newMoney = order.getMoney().add(carMoney.multiply(BigDecimal.valueOf(day)));
    order.setMoney(newMoney);
    return this.updateById(order);
  }

  @Override
  public Boolean checkIdNum(Integer id, String idNum) {
    String orderCusIdNum = orderMapper.getCusIdNumById(id);
    return StrUtil.equals(idNum.substring(6), orderCusIdNum);
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
