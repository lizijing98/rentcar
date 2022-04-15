package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.LoginLog;
import com.rentcar.bean.User;
import com.rentcar.mapper.LoginLogMapper;
import com.rentcar.service.LoginLogService;
import com.rentcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>
    implements LoginLogService {

  private UserService userService;

  @Override
  public <E extends IPage<LoginLog>> E page(E page) {
    E page1 = super.page(page);
    page1.setRecords(this.loginLogHandler(page1.getRecords()));
    return page1;
  }

  @Override
  public <E extends IPage<LoginLog>> E page(E page, Wrapper<LoginLog> queryWrapper) {
    E page1 = super.page(page, queryWrapper);
    page1.setRecords(this.loginLogHandler(page1.getRecords()));
    return page1;
  }

  public List<LoginLog> loginLogHandler(List<LoginLog> loginLogList) {
    Map<Integer, String> map =
        userService.list().stream().collect(Collectors.toMap(User::getId, User::getUsername));
    loginLogList.forEach(loginLog -> loginLog.setUsername(map.get(loginLog.getUser())));
    return loginLogList;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
