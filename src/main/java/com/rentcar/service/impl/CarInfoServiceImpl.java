package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.CarInfo;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.CarInfoMapper;
import com.rentcar.service.CarInfoService;
import com.rentcar.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * service
 *
 * @author zyt
 * @date 2021-04-09
 */
@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoMapper, CarInfo> implements CarInfoService {

    private CarTypeService carTypeService;

    @Autowired
    public void setCarTypeService(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    @Override
    public boolean save(CarInfo entity) {
        this.saveOrUpdateBefore(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(CarInfo entity) {
        this.saveOrUpdateBefore(entity);
        return super.updateById(entity);
    }

    private void saveOrUpdateBefore(CarInfo entity) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", "0");
        queryWrapper.eq("plate_number", entity.getPlateNumber());
        final CarInfo carInfo = getBaseMapper().selectOne(queryWrapper);
        if (carInfo != null && !entity.getId().equals(carInfo.getId())) {
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
}