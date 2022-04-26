package com.rentcar.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Check;
import com.rentcar.bean.search.CheckSearchFrom;
import com.rentcar.service.CheckService;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lzj
 */
@RestController
@RequestMapping("/api/check")
@Slf4j
public class CheckController {

  @Resource private CheckService checkService;

  @PostMapping
  public Meg create(@RequestBody Check check) {
    checkService.init(check);
    return Meg.success();
  }

  @PutMapping("/{id}")
  public Meg update(@RequestBody Check check, @PathVariable Integer id) {
    check.setId(id);
    return checkService.updateById(check) ? Meg.success() : Meg.fail();
  }

  @PostMapping("/page")
  public Meg page(@RequestBody CheckSearchFrom searchFrom) {
    log.info("搜索检修单:{}", searchFrom);
    final Page<Check> page = checkService.page(searchFrom.getPage(), searchFrom.queryWrapper());
    return Meg.success().add("data", page);
  }

  @PostMapping("/{id}/state/{state}")
  public Meg changeState(@PathVariable Integer id, @PathVariable Integer state) {
    return checkService.changeState(id, state) ? Meg.success() : Meg.fail();
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
}
