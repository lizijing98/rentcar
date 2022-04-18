package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Check;

/**
 * 检查单 Service 接口
 *
 * @author Lizijing
 * @date 2022/04/18
 */
public interface CheckService extends IService<Check> {

  /**
   * 创建检查单
   *
   * @param check 检查单
   */
  void init(Check check);

  /**
   * 创建事故流程
   *
   * @param check 检查单
   * @return 结果
   */
  boolean accident(Check check);
}
