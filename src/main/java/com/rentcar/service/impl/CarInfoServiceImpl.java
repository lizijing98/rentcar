package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.CarInfo;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.CarInfoMapper;
import com.rentcar.service.CarInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-09
 */
@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoMapper, CarInfo>
		implements CarInfoService {

	@Resource
	private CarInfoMapper carInfoMapper;

	@Override
	public boolean save(CarInfo entity) {
		this.saveBefore(entity);
		return super.save(entity);
	}

	@Override
	public boolean updateById(CarInfo entity) {
//		this.saveOrUpdateBefore(entity);
		return super.updateById(entity);
	}

	private void saveBefore(CarInfo entity) {
		QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("deleted", "0");
		queryWrapper.eq("plate_number", entity.getPlateNumber());
		CarInfo carInfo = getBaseMapper().selectOne(queryWrapper);
		if (carInfo != null && entity.getPlateNumber().equals(carInfo.getPlateNumber())) {
			throw new BusinessException("该车牌号已经被登记，请重新检查。");
		}
	}

	@Override
	public Integer getCountByTypeIds(List<Serializable> typeIds) {
		QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("deleted", "0");
		queryWrapper.in(typeIds.size() > 0, "car_type", typeIds);
		return this.list(queryWrapper).size();
	}

	@Override
	public CarInfo getOneById(String id) {
		return carInfoMapper.getOneById(id);
	}

	@Override
	public BigDecimal getMoneyById(Integer id) {
		return carInfoMapper.getMoneyById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean changeCarStatus(Integer id, String status) {
		CarInfo carInfo=carInfoMapper.selectById(id);
		carInfo.setStatus(status);
		return this.updateById(carInfo);
	}
}
