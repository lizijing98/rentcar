package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper
 *
 * @author lzj
 * @date 2021-04-19
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	/**
	 * 通过 OrderNum 查询订单
	 *
	 * @param orderNum 订单号
	 * @return Order 实体
	 */
	Order getOneByOrderNum(String orderNum);

	/**
	 * 通过 ID 查询订单身份证号码
	 *
	 * @param id 订单 ID
	 * @return 身份证号码
	 */
	String getCusIdNumById(Integer id);
}
