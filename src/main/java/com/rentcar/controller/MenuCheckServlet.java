package com.rentcar.controller;

import com.rentcar.service.MenuCheckService;
import com.rentcar.util.Meg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lzj
 */
@Controller
public class MenuCheckServlet {
  @Autowired private MenuCheckService menuCheckService;

  /** 根据角色的id自动加载数据库存贮的关联数据到页面 */
  @ResponseBody
  @RequestMapping("/menuJson")
  public List menuJson(@Param("id") Integer id) {
    return menuCheckService.getMenuJsonDate(id);
  }

  /** 根据前台传入的角色id和菜单id数组,对数据库进行操作. */
  @RequestMapping("/menuCheck")
  @ResponseBody
  public Meg menuCheck(@Param("roleId") Integer roleId, @Param("menuId") Integer[] menuId) {
    return menuCheckService.menuCheck(roleId, menuId);
  }
}
