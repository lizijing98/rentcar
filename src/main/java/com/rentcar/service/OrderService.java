package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-19
 */
public interface OrderService extends IService<Order> {

  /**
   * 初始化订单
   *
   * @param carInfoId 车辆 ID
   * @param customerId 顾客 ID
   * @param day 租借时间
   * @return 结果
   */
  boolean initOrder(Serializable carInfoId, Serializable customerId, Integer day);

  /**
   * 修改状态
   *
   * @param id 订单 ID
   * @param state 状态
   * @param feedback 回调？
   * @return 结果
   */
  boolean updateState(Integer id, Integer state, String feedback);

  /**
   * 通过 OrderNum 查询订单
   *
   * @param orderNum 订单号
   * @return Order 实体
   */
  Order getOneByOrderNum(String orderNum);

  /**
   * 修改订单金额
   *
   * @param id 订单 ID
   * @param money 金额
   * @return 结果
   */
  Boolean updateMoney(Integer id, BigDecimal money);

  /**
   * 延长订单
   *
   * @param id 订单 ID
   * @param day 延长时间
   * @return
   */
  Boolean delay(Integer id, Integer day);
}
