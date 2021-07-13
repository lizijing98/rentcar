package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Order;

import java.io.Serializable;

/**
 * controller 控制器
 *
 * @author zyt
 * @date 2021-04-19
 */
public interface OrderService extends IService<Order> {

    /**
     * 初始化订单
     *
     * @param carInfoId
     * @param customerId
     * @param day
     * @return
     */
    boolean initOrder(Serializable carInfoId, Serializable customerId, Integer day);

    /**
     * 修改状态
     *
     * @param id
     * @param state
     * @param feedback
     * @return
     */
    boolean updateState(Integer id, Integer state, String feedback);
}