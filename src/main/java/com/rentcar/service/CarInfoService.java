package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.CarInfo;

import java.io.Serializable;
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
     * @param asList
     * @return
     */
    Integer getCountByTypeIds(List<Serializable> asList);
}