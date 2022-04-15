package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rentcar.bean.Role;
import com.rentcar.bean.UserRole;
import com.rentcar.mapper.RoleMapper;
import com.rentcar.mapper.UserRoleMapper;
import com.rentcar.service.RoleCheckService;
import com.rentcar.util.Meg;
import com.rentcar.util.RoleCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzj
 */
@Service
public class RoleCheckServiceImpl implements RoleCheckService {
  @Autowired private UserRoleMapper userRoleMapper;
  @Autowired private RoleMapper roleMapper;

  @Override
  public Meg getRoleCheck(Integer userId) {
    Meg meg = Meg.success();
    List<Role> roleList = roleMapper.selectList(new QueryWrapper<>());
    List<UserRole> list = userRoleMapper.selectByUserId(userId);
    String[] ss = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      ss[i] = list.get(i).getRoleId().toString();
    }
    meg.add("data", RoleCheckUtil.getRoleCheck(roleList));
    meg.add("check", ss);
    return meg;
  }

  @Override
  public Meg roleCheck(Integer userId, Integer[] roleId, Integer type) {
    if (type == 0) {
      for (int i = 0; i < roleId.length; i++) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId[i]);
        userRole.setUserId(userId);
        userRoleMapper.insert(userRole);
      }
    } else {
      QueryWrapper queryWrapper = new QueryWrapper();
      queryWrapper.eq(roleId.length > 0, "user_id", userId);
      queryWrapper.in(roleId.length > 0, "role_id", roleId);
      userRoleMapper.delete(queryWrapper);
    }
    return Meg.success();
  }
}
