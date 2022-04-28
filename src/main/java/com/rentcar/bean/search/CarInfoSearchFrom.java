package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.CarInfo;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Author: lzj
 */
@Data
public class CarInfoSearchFrom {
	private Integer pageSize;
	private Integer pageNum;
	/**
	 * 车型
	 */
	private String carType;
	/**
	 * 车牌
	 */
	private String plateNumber;
	/**
	 * 制造商
	 */
	private String manufacturer;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 最低日租金
	 */
	private String moneyMin;
	/**
	 * 最高日租金
	 */
	private String moneyMax;

	public Page<CarInfo> getPage() {
		return new Page<>(pageNum, pageSize);
	}

	public QueryWrapper<CarInfo> queryWrapper() {
		QueryWrapper<CarInfo> queryWrapper = new QueryWrapper();
		queryWrapper.eq("info.deleted", "0");
		queryWrapper.like(!StringUtils.isEmpty(carType), "type.name", carType);
		queryWrapper.like(!StringUtils.isEmpty(plateNumber), "info.plate_number", plateNumber);
		queryWrapper.like(!StringUtils.isEmpty(brand), "info.brand", brand);
		queryWrapper.like(!StringUtils.isEmpty(manufacturer), "info.manufacturer", manufacturer);
		queryWrapper.ge(!StringUtils.isEmpty(moneyMin), "info.money", moneyMin);
		queryWrapper.le(!StringUtils.isEmpty(moneyMax), "info.money", moneyMax);
		return queryWrapper;
	}
}
