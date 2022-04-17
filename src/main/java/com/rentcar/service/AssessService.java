package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Assess;

import java.io.Serializable;

/**
 * Assess Service 接口
 *
 * @author LiZijing
 * @date 2022/4/17
 */
public interface AssessService extends IService<Assess> {

  /**
   * 初始化评价
   *
   * @param orderNum 订单编号
   * @param customerId 顾客 ID
   * @param remake 评价
   * @return 结果
   */
  boolean initAssess(String orderNum, Serializable customerId, String remake);
}
