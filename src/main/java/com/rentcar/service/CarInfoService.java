package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.CarInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-09
 */
public interface CarInfoService extends IService<CarInfo> {

	/**
	 * 根据汽车类型获取总条数
	 *
	 * @param asList 该类型的车辆数目
	 * @return 结果
	 */
	Integer getCountByTypeIds(List<Serializable> asList);

	/**
	 * 通过 ID 获取信息
	 *
	 * @param id 车辆 ID
	 * @return 结果
	 */
	CarInfo getOneById(String id);

	/**
	 * 通过 ID 获取租金
	 *
	 * @param id 车辆 ID
	 * @return 结果
	 */
	BigDecimal getMoneyById(Integer id);

	Boolean changeCarStatus(Integer id,String status);
}
