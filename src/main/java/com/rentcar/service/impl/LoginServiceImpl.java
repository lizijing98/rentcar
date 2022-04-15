package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rentcar.bean.*;
import com.rentcar.mapper.MenuMapper;
import com.rentcar.mapper.MenuRoleMapper;
import com.rentcar.mapper.UserMapper;
import com.rentcar.mapper.UserRoleMapper;
import com.rentcar.service.CustomerService;
import com.rentcar.service.LoginLogService;
import com.rentcar.service.LoginService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.RoleList;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author lzj
 */
@Service
public class LoginServiceImpl implements LoginService {
  @Autowired HttpSession httpSession;
  @Autowired private UserMapper userMapper;
  @Autowired private UserRoleMapper userRoleMapper;
  @Autowired private MenuRoleMapper menuRoleMapper;
  @Autowired private MenuMapper menuMapper;
  @Resource private LoginLogService loginLogService;

  @Resource private CustomerService customerService;

  @Override
  public Meg login(String username, String password, String authCode, String ip) {
    Integer b = userMapper.selectAction(username);
    authCode = authCode.toLowerCase();
    boolean bool = authCode.equals(httpSession.getAttribute("authCode"));
    if (!bool) {
      return Meg.Message(1000, "验证码错误！");
    }
    if (b == null || b == 0) {
      return Meg.userAction();
    }
    User user = userMapper.selectByUserAndPass(username, password);
    if (user != null) {
      LoginLog loginLog = new LoginLog();
      loginLog.setLoginIp(ip);
      loginLog.setLoginTime(new Date());
      loginLog.setUser(user.getId());
      loginLogService.save(loginLog);
      httpSession.removeAttribute("customerId");
      httpSession.removeAttribute("customer");
      httpSession.setAttribute("user", user);
      httpSession.setAttribute("userId", user.getId());
      return Meg.loginSuccess();
    } else {
      return Meg.loginFile();
    }
  }

  @Override
  public Meg homeJson(HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    if (user == null) {
      return Meg.file("请先登录！！");
    }
    List roleId = getRole(user.getId());
    return Meg.success().add("data", getMenuJson(0, roleId));
  }

  public List getRole(Integer userId) {
    List roleId = new RoleList();
    List<UserRole> roleList = userRoleMapper.selectByUserId(userId);
    for (int i = 0; i < roleList.size(); i++) {
      List menuIdList = menuRoleMapper.selectByRoleId(roleList.get(i).getRoleId());
      roleId.addAll(menuIdList);
    }
    return roleId;
  }

  public List getMenuJson(Integer id, List menuIdList) {
    List list = new ArrayList();
    List<Menu> menuList = menuMapper.getMenuByParentId(id);
    Map map;
    for (int i = 0; i < menuList.size(); i++) {
      if (menuIdList.contains(menuList.get(i).getId())) {
        map = new HashMap();
        map.put("id", menuList.get(i).getId());
        map.put("name", menuList.get(i).getMenuName());
        if (getMenuJson(menuList.get(i).getId(), menuIdList).size() > 0) {
          map.put("children", getMenuJson(menuList.get(i).getId(), menuIdList));
        } else {
          map.put("url", menuList.get(i).getMenuUrl());
        }
        list.add(map);
      }
    }
    return list;
  }

  @Override
  public Meg loginCustomer(String username, String password, String authCode, String ip) {
    QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    Customer customer = customerService.getOne(queryWrapper);
    authCode = authCode.toLowerCase();
    boolean bool = authCode.equals(httpSession.getAttribute("authCode"));
    if (!bool) {
      return Meg.Message(1000, "验证码错误！");
    }
    if (customer == null) {
      return Meg.userAction();
    }
    queryWrapper.eq("password", password);
    customer = customerService.getOne(queryWrapper);
    if (customer != null) {
      LoginLog loginLog = new LoginLog();
      loginLog.setLoginIp(ip);
      loginLog.setLoginTime(new Date());
      loginLog.setUser(customer.getId());
      loginLogService.save(loginLog);
      httpSession.removeAttribute("userId");
      httpSession.removeAttribute("user");
      httpSession.setAttribute("customer", customer);
      httpSession.setAttribute("customerId", customer.getId());
      return Meg.loginSuccess();
    } else {
      return Meg.loginFile();
    }
  }
}
