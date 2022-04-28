package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.CarInfo;
import com.rentcar.bean.CarType;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.CarTypeMapper;
import com.rentcar.service.CarInfoService;
import com.rentcar.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-09
 */
@Service
public class CarTypeServiceImpl extends ServiceImpl<CarTypeMapper, CarType>
		implements CarTypeService {

	private CarInfoService carInfoService;

	@Override
	public boolean save(CarType entity) {
		saveOrUpdateBefore(entity);
		return super.save(entity);
	}

	private void saveOrUpdateBefore(CarType entity) {
		QueryWrapper<CarType> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", entity.getName());
		final CarType carType = getBaseMapper().selectOne(queryWrapper);
		if (carType != null) {
			throw new BusinessException("汽车类型已经是存在");
		}
	}

	@Override
	public boolean removeById(Serializable id) {
		this.removeBefore(id);
		return super.removeById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		this.removeBefore(idList.toArray());
		return super.removeByIds(idList);
	}

	private void removeBefore(Serializable... ids) {
		// 获取是否被占用
		Integer count = carInfoService.getCountByTypeIds(Arrays.asList(ids));
		if (count > 0) {
			throw new BusinessException("该类型已经被使用，无法删除。");
		}
	}

	@Override
	public List<CarType> listAndInfoList() {
		final List<CarType> list = super.list();
		list.forEach(
				carType -> {
					QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("info.deleted", "0");
					queryWrapper.eq("type.deleted", "0");
					queryWrapper.eq("car_type", carType.getId());
					List<CarInfo> carInfos = carInfoService.page(new Page<>(1, 8), queryWrapper).getRecords();
					carType.setCarInfos(carInfos);
				});
		return list;
	}

	@Autowired
	public void setCarInfoService(CarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}
}
