package com.rentcar.servlet;

import com.rentcar.service.RoleCheckService;
import com.rentcar.util.Meg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lzj
 */
@Controller
public class RoleCheckServlet {

  @Autowired private RoleCheckService roleCheckService;

  @RequestMapping("/roleCheckDate")
  @ResponseBody
  public Meg getSelectRoleCheck(@Param("id") Integer id) {
    return roleCheckService.getRoleCheck(id);
  }

  @RequestMapping("/insertOrDelete")
  @ResponseBody
  public Meg InsertOrDelete(
      @Param("type") Integer type,
      @Param("userId") Integer userId,
      @Param("roleId") Integer[] roleId) {
    return roleCheckService.roleCheck(userId, roleId, type);
  }
}
