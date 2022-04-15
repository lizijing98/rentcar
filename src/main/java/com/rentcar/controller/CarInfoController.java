package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.CarInfo;
import com.rentcar.bean.search.CarInfoSearchFrom;
import com.rentcar.service.CarInfoService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-09
 */
@RestController
@RequestMapping("/car-info")
public class CarInfoController {


    private CarInfoService service;

    @Autowired
    public void setService(CarInfoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Meg getById(@PathVariable Integer id) {
        final CarInfo carInfo = service.getById(id);
        return Meg.success().add("data", carInfo);
    }

    @PutMapping("/{id}")
    public Meg update(@RequestBody CarInfo carInfo) {
        boolean bool = service.updateById(carInfo);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/page")
    public Meg page(@RequestBody CarInfoSearchFrom searchFrom) {
        final Page<CarInfo> page = service.page(searchFrom.getPage(), searchFrom.queryWrapper());
        return Meg.success().add("data", page);
    }

    @DeleteMapping("/{id}")
    public Meg del(@PathVariable Integer id) {
        boolean bool = service.removeById(id);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping
    public Meg insert(@RequestBody CarInfo carInfo) {
        boolean bool = service.save(carInfo);
        return bool ? Meg.success() : Meg.file();
    }

    @DeleteMapping
    public Meg del(@RequestParam("id") Integer[] ids) {
        final boolean bool = service.removeByIds(Arrays.asList(ids));
        return bool ? Meg.success() : Meg.file();
    }
}