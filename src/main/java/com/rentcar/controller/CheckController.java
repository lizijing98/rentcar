package com.rentcar.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rentcar.bean.Check;
import com.rentcar.service.CheckService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzj
 */
@RestController
@RequestMapping("/api/check")
public class CheckController {

  private CheckService checkService;

  @PostMapping
  public Meg create(@RequestBody Check check) {
    checkService.init(check);
    return Meg.success();
  }

  @PostMapping("/accident")
  public Meg accident(@RequestBody Check check) {
    return checkService.accident(check) ? Meg.success() : Meg.fail();
  }

  @GetMapping
  public Meg get(Integer orderId) {
    QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("order_id", orderId);
    queryWrapper.orderByDesc("create_time");
    final Check one = checkService.list(queryWrapper).get(0);
    return Meg.success().add("data", one);
  }

  @Autowired
  public void setCheckService(CheckService checkService) {
    this.checkService = checkService;
  }
}
