package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Assess;

import java.io.Serializable;
import java.util.List;

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

  /**
   * 根据车辆 ID 获取评价列表
   *
   * @param id 车辆 ID
   * @return 结果
   */
  List<Assess> getAssessByCarId(String id);

  /**
   * 创建评价
   *
   * @param assess 评价
   */
  void init(Assess assess);

  /**
   * 修改评价状态
   *
   * @param orderNum 订单号
   * @param status 评价状态
   * @return 结果
   */
  Boolean setStatus(String orderNum, Integer status);
}
