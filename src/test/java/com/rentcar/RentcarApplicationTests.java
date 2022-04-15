package com.rentcar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.LoginLog;
import com.rentcar.service.CarInfoService;
import com.rentcar.service.LoginLogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class RentcarApplicationTests {

  @Resource private LoginLogService loginLogService;
  @Resource private CarInfoService carInfoService;

  @Test
  void contextLoads() {
    LoginLog loginLog = new LoginLog();
    loginLog.setLoginIp("127.0.0.1");
    loginLog.setUser(2);
    loginLog.setLoginTime(new Date());
    loginLogService.save(loginLog);
  }

  @Test
  void query() {
    List<LoginLog> list = loginLogService.page(new Page<>(1, 100)).getRecords();
    System.out.println(list);
  }

  @Test
  public void carInfo() {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("car_type", 2);
    final Page page = carInfoService.page(new Page<>(0, 10), queryWrapper);
    System.out.println(page.getRecords());
    System.out.println(page.getTotal());
  }
}
