package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.CarInfo;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * mapper
 *
 * @author lzj
 * @date 2021-04-09
 */
@Mapper
public interface CarInfoMapper extends BaseMapper<CarInfo> {

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
}
