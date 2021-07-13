package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.CarType;
import com.rentcar.bean.search.CarTypeSearchFrom;
import com.rentcar.service.CarTypeService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * controller 控制器
 *
 * @author zyt
 * @date 2021-04-09
 */
@RestController
@RequestMapping("/car-type")
public class CarTypeController {

    private CarTypeService service;

    @Autowired
    public void setService(CarTypeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Meg getById(@PathVariable Integer id) {
        final CarType bean = service.getById(id);
        return Meg.success().add("data", bean);
    }

    @PutMapping("/{id}")
    public Meg update(@RequestBody CarType bean) {
        Boolean bool = service.updateById(bean);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/page")
    public Meg page(@RequestBody CarTypeSearchFrom searchFrom) {
        final Page<CarType> page = service.page(searchFrom.getPage(), searchFrom.queryWrapper());
        return Meg.success().add("data", page);
    }

    @GetMapping("/list")
    public Meg list() {
        final List<CarType> list = service.list();
        return Meg.success().add("data", list);
    }

    @DeleteMapping("/{id}")
    public Meg del(@PathVariable Integer id) {
        Boolean bool = service.removeById(id);
        return bool ? Meg.success() : Meg.file();
    }

    @DeleteMapping
    public Meg del(@RequestParam("id") Integer[] ids) {
        Boolean bool = service.removeByIds(Arrays.asList(ids));
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping
    public Meg insert(@RequestBody CarType bean) {
        Boolean bool = service.save(bean);
        return bool ? Meg.success() : Meg.file();
    }
}