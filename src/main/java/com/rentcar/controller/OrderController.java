package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Order;
import com.rentcar.bean.search.OrderSearchFrom;
import com.rentcar.service.OrderService;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * controller 控制器
 *
 * @author zyt
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
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/page")
    public Meg page(@RequestBody OrderSearchFrom searchFrom) {
        log.info("搜索订单：{}", searchFrom);
        final Page<Order> page = service.page(searchFrom.getPage(), searchFrom.queryWrapper());
        return Meg.success().add("data", page);
    }

    @DeleteMapping("/{id}")
    public Meg del(@PathVariable Integer id) {
        boolean bool = service.removeById(id);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/{id}/state/{state}")
    public Meg handler(@PathVariable Integer id, @PathVariable Integer state, String feedback) {
        boolean bool = service.updateState(id, state, feedback);
        return bool ? Meg.success() : Meg.file();
    }


    @PostMapping("/initOrder")
    public Meg initOrder(@RequestParam("carInfoId") Integer carInfoId, @RequestParam("day") Integer day, HttpSession httpSession) {
        Object customerId = httpSession.getAttribute("customerId");
        if (customerId == null) {
            Meg meg = new Meg();
            meg.setMessage("请先登录！");
            meg.setCode(403);
            return meg;
        }
        boolean bool = service.initOrder(carInfoId, customerId + "", day);
        return bool ? Meg.success() : Meg.file();
    }

    @Autowired
    public void setOrderService(OrderService service) {
        this.service = service;
    }
}