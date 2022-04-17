package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.LoginLog;
import com.rentcar.service.LoginLogService;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/login-log/")
public class LoginLogController {
  /** 登录service */
  private final LoginLogService loginLogService;

  /**
   * 使用这种方式时，spring自动注入，不用手动在autowired
   *
   * @param loginLogService
   */
  public LoginLogController(LoginLogService loginLogService) {
    log.debug("类在被初始化-service:{}", loginLogService);
    this.loginLogService = loginLogService;
  }

  @PostMapping("/page")
  public Meg page(
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "sizeSize", defaultValue = "10") Integer pageSize) {
    Page<LoginLog> page = new Page<>(pageNum, pageSize);
    return Meg.success().add("data", loginLogService.page(page));
  }

  @PostMapping
  public Meg insert(@RequestBody LoginLog loginLog) {
    boolean i = loginLogService.save(loginLog);
    return i ? Meg.success() : Meg.fail();
  }

  @PutMapping("/{id}")
  public Meg update(@RequestBody LoginLog loginLog) {
    boolean i = loginLogService.updateById(loginLog);
    return i ? Meg.success() : Meg.fail();
  }

  /**
   * 理论上登录日志不应该被删除
   *
   * @param id
   * @return
   */
  @DeleteMapping("/{id}")
  public Meg remove(@PathVariable Integer id) {
    boolean i = loginLogService.removeById(id);
    return i ? Meg.success() : Meg.fail();
  }
}
