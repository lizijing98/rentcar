package com.rentcar.servlet;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Role;
import com.rentcar.service.RoleService;
import com.rentcar.util.Meg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleServlet {

  @Autowired private RoleService roleService;

  @ResponseBody
  @RequestMapping("/roleData")
  public Meg roleData(@Param("page") Integer page, @Param("limit") Integer limit) {
    Page<Role> page1 = new Page<>(page, limit);
    return Meg.success().add("data", roleService.page(page1));
  }

  @ResponseBody
  @RequestMapping("/roleInsert")
  public Meg roleInsert(Role role) {
    boolean i = roleService.save(role);
    return i ? Meg.success() : Meg.fail();
  }

  @ResponseBody
  @RequestMapping("/roleUpdate")
  public Meg roleUpdate(Role role) {
    boolean i = roleService.updateById(role);
    return i ? Meg.success() : Meg.fail();
  }

  @ResponseBody
  @RequestMapping("/roleDelete")
  public Meg roleDelete(@Param("id") Integer id) {
    boolean i = roleService.removeById(id);
    return i ? Meg.success() : Meg.fail();
  }

  @ResponseBody
  @RequestMapping("/roleActivate")
  public Meg menuAction(@Param("id") Integer id, @Param("roleActivate") String roleActivate) {
    roleService.action(id, roleActivate);
    return Meg.success();
  }
}
