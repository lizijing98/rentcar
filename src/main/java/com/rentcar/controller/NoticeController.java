package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Notice;
import com.rentcar.bean.search.NoticeSearchFrom;
import com.rentcar.service.NoticeService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-19
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
  private final NoticeService service;

  @Autowired
  public NoticeController(NoticeService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public Meg getById(@PathVariable Integer id) {
    final Notice bean = service.getById(id);
    return Meg.success().add("data", bean);
  }

  @PutMapping("/{id}")
  public Meg update(@RequestBody Notice bean) {
    boolean bool = service.updateById(bean);
    return bool ? Meg.success() : Meg.fail();
  }

  @PostMapping("/page")
  public Meg page(@RequestBody NoticeSearchFrom searchFrom) {
    final Page<Notice> page = service.page(searchFrom.getPage(), searchFrom.queryWrapper());
    return Meg.success().add("data", page);
  }

  @DeleteMapping("/{id}")
  public Meg del(@PathVariable Integer id) {
    boolean bool = service.removeById(id);
    return bool ? Meg.success() : Meg.fail();
  }

  @PostMapping
  public Meg insert(@RequestBody Notice bean) {
    boolean bool = service.save(bean);
    return bool ? Meg.success() : Meg.fail();
  }
}
