package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Check;

/**
 * @author lzj
 */
public interface CheckMapper extends BaseMapper<Check> {
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
	Integer updateState(Integer id, Integer state);

	/**
	 * 根据订单 ID 获取检查单 ID
	 * @param orderId 订单 ID
	 * @return 检查单 ID
	 */
	Integer getIdByOrderId(Integer orderId);

	/**
	 * 通过订单号获取检查单
	 * @param orderNum 订单号
	 * @return 检查单
	 */
	Check getOneByOrderNum(String orderNum);
}
