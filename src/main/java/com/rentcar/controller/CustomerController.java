package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Customer;
import com.rentcar.bean.UpdatePass;
import com.rentcar.bean.search.CustomerSearchFrom;
import com.rentcar.exception.BusinessException;
import com.rentcar.service.CustomerService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-19
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService service;

    @GetMapping("/{id}")
    public Meg getById(@PathVariable Integer id) {
        final Customer bean = service.getById(id);
        return Meg.success().add("data", bean);
    }

    @PutMapping("/{id}")
    public Meg update(@RequestBody Customer bean, @PathVariable Integer id) {
        bean.setId(id);
        boolean bool = service.updateById(bean);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/page")
    public Meg page(@RequestBody CustomerSearchFrom searchFrom) {
        final Page<Customer> page = service.page(searchFrom.getPage(), searchFrom.queryWrapper());
        return Meg.success().add("data", page);
    }

    @DeleteMapping("/{id}")
    public Meg del(@PathVariable Integer id) {
        boolean bool = service.removeById(id);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping
    public Meg insert(@RequestBody Customer bean) {
        boolean bool = service.save(bean);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/{id}/active")
    public Meg active(String active, @PathVariable Integer id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setActivate(active);
        boolean bool = service.updateById(customer);
        return bool ? Meg.success() : Meg.file();
    }

    @PostMapping("/topUp")
    public Meg topUp(Double money, HttpSession httpSession) {
        Integer customerId = (Integer) httpSession.getAttribute("customerId");
        final Customer customer = service.getById(customerId);
        customer.setMoney(customer.getMoney().add(BigDecimal.valueOf(money)));
        service.updateById(customer);
        return Meg.success();
    }

    /**
     * 更改密码
     */
    @PutMapping("/updatePassword")
    public Meg updatePassword(@RequestBody UpdatePass updatePass, HttpSession httpSession) {
        String oldPassword = updatePass.getOldPassword();
        String newPassword = updatePass.getNewPassword();
        String newPassword2 = updatePass.getNewPassword2();
        Integer customerId = (Integer) httpSession.getAttribute("customerId");
        final Customer customer = service.getById(customerId);
        if (!customer.getPassword().equals(oldPassword)) {
            System.out.println(customer);
            return Meg.file("密码错误");
        }
        if (newPassword.equals(newPassword2)) {
            customer.setPassword(newPassword);
            service.updateById(customer);
            httpSession.setAttribute("customer", customer);
            return Meg.success();
        } else {
            return Meg.file("密码不一致");
        }
    }

    @Autowired
    public void setService(CustomerService service) {
        this.service = service;
    }
}