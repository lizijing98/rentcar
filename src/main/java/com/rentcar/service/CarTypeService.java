package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.CarType;

import java.util.List;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-09
 */
public interface CarTypeService extends IService<CarType> {
    /**
     * 查询类型并且连带查询其子表前20条
     *
     * @return
     */
    List<CarType> listAndInfoList();
}