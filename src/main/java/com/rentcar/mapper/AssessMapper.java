package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Assess;

import java.util.List;

/**
 * AssessMapper
 *
 * @author LiZijing
 * @date 2022/4/17
 */
public interface AssessMapper extends BaseMapper<Assess> {
	/**
	 * 根据车辆 ID 获取评价列表
	 *
	 * @param id 车辆 ID
	 * @return 结果
	 */
	List<Assess> getAssessByCarId(String id);

	/**
	 * 根据订单号获取评价
	 *
	 * @param orderNumber 订单号
	 * @return 评价
	 */
	Assess getAssessByOrdNum(String orderNumber);

	/**
	 * 根据订单号获取评价 ID
	 *
	 * @param orderNumber 订单号
	 * @return 评价 ID
	 */
	Integer getIdByOrderNum(String orderNumber);
}
