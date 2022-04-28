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

	/**
	 * 通过订单 ID 获取检查单
	 *
	 * @param orderId 订单 ID
	 * @return 订单实体
	 */
	Check getOneByOrderId(Integer orderId);

	/**
	 * 修改检查单状态
	 *
	 * @param id    检查单 ID
	 * @param state 检查单状态
	 * @return 结果
	 */
	boolean changeState(Integer id, Integer state);
}
