package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Delay;
import com.rentcar.bean.Order;
import com.rentcar.bean.VO.InitOrderVO;
import com.rentcar.bean.search.OrderSearchFrom;
import com.rentcar.constant.SystemConstant;
import com.rentcar.service.OrderService;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-19
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

  private OrderService service;

  @GetMapping("/{id}")
  public Meg getById(@PathVariable Integer id) {
    final Order bean = service.getById(id);
    return Meg.success().add("data", bean);
  }

  @PutMapping("/{id}")
  public Meg update(Order bean) {
    boolean bool = service.updateById(bean);
    return bool ? Meg.success() : Meg.fail();
  }

  @PostMapping("/page")
  public Meg page(@RequestBody OrderSearchFrom searchFrom, HttpSession httpSession) {
    if (httpSession.getAttribute(SystemConstant.CUSTOMER_ID) == null
        && httpSession.getAttribute(SystemConstant.USER_ID) == null) {
      return Meg.noLogin().add("data", null);
    }
    log.info("搜索订单：{}", searchFrom);
    final Page<Order> page = service.page(searchFrom.getPage(), searchFrom.queryWrapper());
    return Meg.success().add("data", page);
  }

  @DeleteMapping("/{id}")
  public Meg del(@PathVariable Integer id) {
    boolean bool = service.removeById(id);
    return bool ? Meg.success() : Meg.fail();
  }

  @PostMapping("/{id}/state/{state}")
  public Meg handler(@PathVariable Integer id, @PathVariable Integer state, String feedback) {
    boolean bool = service.updateState(id, state, feedback);
    return bool ? Meg.success() : Meg.fail();
  }

  @PostMapping("/initOrder")
  public Meg initOrder(@RequestBody InitOrderVO initOrderVO, HttpSession httpSession) {
    Object customerId = httpSession.getAttribute("customerId");
    if (customerId == null) {
      return Meg.noLogin();
    }
    boolean bool = service.initOrder(initOrderVO, customerId + "");
    return bool ? Meg.success() : Meg.fail();
  }

  @PostMapping("/delay")
  public Meg delay(@RequestBody Delay delay) {
    return service.delay(delay.getOrderId(), delay.getDay());
  }

  @PostMapping("/{id}/idCheck")
  public Meg checkId(@RequestBody String idNum, @PathVariable Integer id) {
    return service.checkIdNum(id, idNum) ? Meg.success() : Meg.fail("身份证验证失败");
  }

  @Autowired
  public void setOrderService(OrderService service) {
    this.service = service;
  }
}
