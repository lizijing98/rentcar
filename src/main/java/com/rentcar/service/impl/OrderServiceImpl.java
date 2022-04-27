package com.rentcar.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.CarInfo;
import com.rentcar.bean.Check;
import com.rentcar.bean.Customer;
import com.rentcar.bean.Order;
import com.rentcar.bean.VO.InitOrderVO;
import com.rentcar.enums.OrderStatus;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.OrderMapper;
import com.rentcar.service.*;
import com.rentcar.util.Meg;
import com.rentcar.util.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

  @Resource private CustomerService customerService;
  @Resource private CarInfoService carInfoService;
  @Resource private OrderUtils orderUtils;
  @Resource private CheckService checkService;
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
    if (endDate.before(startDate)) {
      throw new BusinessException("结束时间不能早于开始时间");
    }
    // 定期
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
    // 申请还车
    if (state.equals(OrderStatus.ORD_STA_CHECKING.getCode())) {
      applyReturn(state, order);
    }
    // 申请借车
    if (state.equals(OrderStatus.ORD_STA_PROGRESSING.getCode())
        || state.equals(OrderStatus.ORD_STA_FAILED_BORROW.getCode())) {
      applyBorrow(state, order);
    }
    // 拒绝还车
    if (state.equals(OrderStatus.ORD_STA_REFUSE_RETURN.getCode())) {
      if (order.getState().equals(OrderStatus.ORD_STA_APPLY_RETURN.getCode())) {
        order.setState(state);
        this.updateById(order);
      } else {
        throw new BusinessException("订单处理失败！请稍后重试。");
      }
    }
    // 同意还车
    if (state == 6) {
      if (order.getState().equals(9) || order.getState().equals(7)) {
        log.info("*****还车开始*****");
        order.setState(state);
        CarInfo carInfo = carInfoService.getById(order.getCarInfoId());
        // 订单预计结束时间
        LocalDateTime endDate = order.getEndDate();
        // 车辆出库时间
        LocalDateTime outDate = order.getOutDate();
        // 车辆入库时间
        LocalDateTime inDate = LocalDateTime.now();
        order.setInDate(inDate);
        // 订单实际结束时间
        order.setEndDate(inDate);
        BigDecimal money = carInfo.getMoney();
        // 正常租期=出库时间 —— 入库时间
        double leaseTerm = LocalDateTimeUtil.between(inDate, outDate, ChronoUnit.HOURS) / 24.0;
        // 不足一天的按一天处理
        if (leaseTerm < 1.0) {
          leaseTerm = 1.0;
        }
        order.setTenancyTerm(leaseTerm);
        // 正常租金
        BigDecimal rent = BigDecimal.valueOf(leaseTerm).multiply(money);
        order.setMoney(rent);
        // 滞纳金 入库时间 > 订单预期结束时间
        BigDecimal penalty = BigDecimal.ZERO;
        double day = 0.0;
        if (inDate.isAfter(endDate)) {
          day = LocalDateTimeUtil.between(endDate, inDate, ChronoUnit.HOURS) / 24.0 + 1.0;
          // 2倍的日租金罚金
          penalty = money.multiply(BigDecimal.valueOf(day)).multiply(BigDecimal.valueOf(2));
        }
        order.setPenalty(penalty);
        // 检修单罚金
        BigDecimal fine = order.getFine();
        // 总金额
        BigDecimal total = rent.add(penalty).add(fine);
        total = total.setScale(2, RoundingMode.UP);
        order.setTotal(total);
        log.info(
            "订单 ID:{},日租金:{},租期:{},逾期:{},正常租金:{},罚金:{},赔付金额:{},总金额：{}",
            id,
            money,
            order.getTenancyTerm(),
            day,
            rent,
            penalty,
            fine,
            total);
        Customer customer = customerService.getById(order.getCustomerId());
        // 减去租金
        customer.setMoney(customer.getMoney().subtract(total).add(customer.getFreeze()));
        customer.setFreeze(BigDecimal.ZERO);
        customerService.updateById(customer);
        if (checkService.getOneByOrderId(order.getId()).getState() == 0) {
          throw new BusinessException("检查单未处理");
        }
        this.updateById(order);
        // 保存评价
        assessService.setStatus(order.getOrderNumber(), 0);
      } else {
        throw new BusinessException("订单处理失败！请稍后重试。");
      }
    }
    // 提交事故
    if (state == 7) {
      if (order.getState().equals(2)) {
        // 检修单罚金
        Check check = checkService.getOneByOrderId(order.getId());
        BigDecimal fine = check.getMoney();
        order.setFine(fine);
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
    // 订单完成复查
    if (state.equals(OrderStatus.ORD_STA_RECHECK.getCode())) {
      if (order.getState().equals(OrderStatus.ORD_STA_CHECKING.getCode())
          || order.getState().equals(OrderStatus.ORD_STA_ACCIDENT.getCode())) {
        // 检修单罚金
        Check check = checkService.getOneByOrderId(order.getId());
        BigDecimal fine = check.getMoney();
        order.setFine(fine);
        order.setState(state);
        this.updateById(order);
      } else {
        throw new BusinessException("订单状态异常");
      }
    }

    // 修改汽车状态
    CarInfo carInfo = carInfoService.getById(order.getCarInfoId());
    if (state == 6 || state == 8 || state == 3) {
      carInfo.setStatus("未出租");
      carInfoService.updateById(carInfo);
    } else if (state == 7) {
      carInfo.setStatus("事故处理中");
      carInfoService.updateById(carInfo);
    } else if (state.equals(OrderStatus.ORD_STA_PROGRESSING.getCode())) {
      carInfo.setStatus("已出租");
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
  public Meg delay(Integer id, String day) {
    log.info("延长订单时间: ");
    Order order = this.getById(id);
    LocalDateTime oldEndDate = order.getEndDate();
    LocalDateTime newEndDate = LocalDateTimeUtil.parse(day, "yyyy-MM-dd HH:mm:ss");
    if (oldEndDate.isAfter(newEndDate)) {
      return Meg.fail("延期日期不可早于原结束日期");
    }
    if (order.getState() != 2) {
      log.error("订单状态非进行中");
      return Meg.fail("订单不可延期");
    }
    // 修改结束时间
    order.setEndDate(newEndDate);
    // 修改定期
    LocalDateTime beginDate = order.getStartDate();
    double newDay = LocalDateTimeUtil.between(beginDate, newEndDate, ChronoUnit.HOURS) / 24.0 + 1;
    // 修改订单金额
    BigDecimal carMoney = carInfoService.getMoneyById(order.getCarInfoId());
    BigDecimal newMoney = order.getMoney().add(carMoney.multiply(BigDecimal.valueOf(newDay)));
    order.setMoney(newMoney);
    if (this.updateById(order)) {
      return Meg.success();
    }
    return Meg.fail();
  }

  @Override
  public Boolean checkIdNum(Integer id, String idNum) {
    String orderCusIdNum = orderMapper.getCusIdNumById(id);
    return StrUtil.equals(idNum.substring(6), orderCusIdNum);
  }

  @Transactional(rollbackFor = Exception.class)
  protected void applyReturn(Integer state, Order order) {
    if (order.getState().equals(OrderStatus.ORD_STA_PROGRESSING.getCode())) {
      order.setState(state);
      if (!this.updateById(order)) {
        throw new BusinessException("申请还车失败!");
      }
    } else if (order.getState().equals(OrderStatus.ORD_STA_REFUSE_RETURN.getCode())) {
      order.setState(state);
      if (!this.updateById(order)) {
        throw new BusinessException("重新申请还车失败!");
      }
    } else {
      throw new BusinessException("其他异常!");
    }
  }

  @Transactional(rollbackFor = Exception.class)
  protected void applyBorrow(Integer state, Order order) {
    if (order.getState().equals(OrderStatus.ORD_STA_APPLY_BORROW.getCode())) {
      // 预订单转订单
      if (state.equals(OrderStatus.ORD_STA_PROGRESSING.getCode())) {
        order.setOutDate(LocalDateTime.now());
      }
      Customer customer = customerService.getById(order.getCustomerId());
      CarInfo carInfo = carInfoService.getById(order.getCarInfoId());
      // 修改用户余额
      customer.setFreeze(carInfo.getCashPledge());
      customer.setMoney(customer.getMoney().subtract(carInfo.getCashPledge()));
      order.setState(state);
      if (!customerService.updateById(customer)) {
        throw new BusinessException("用户信息处理失败");
      }
      if (!this.updateById(order)) {
        throw new BusinessException("订单处理失败！请稍后重试。");
      }
    } else if (order.getState().equals(OrderStatus.ORD_STA_CHECKING.getCode())) {
      // 取消还车
      order.setState(state);
      if (!this.updateById(order)) {
        throw new BusinessException("取消申请失败!");
      }
    } else {
      throw new BusinessException("其他异常!");
    }
  }
}
