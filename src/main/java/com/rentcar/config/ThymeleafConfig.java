package com.rentcar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 该配置类主要用来配置url到页面的配置。
 *
 * @author lzj
 */
@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // 检查单
    registry.addViewController("/check").setViewName("main/check");
    // 还车单
    registry.addViewController("/return").setViewName("main/return");
    // 轮播
    registry.addViewController("/carousel").setViewName("carousel/carousel");
    // 订单
    registry.addViewController("/order").setViewName("order/order");
    // 评价
    registry.addViewController("/assess").setViewName("main/myAssess");
    // 公告管理
    registry.addViewController("/notice").setViewName("notice/notice");
    // 客户列表
    registry.addViewController("/customer").setViewName("customer/customer");
    // 汽车分类列表
    registry.addViewController("/carInfo").setViewName("car/car");
    // 汽车信息列表
    registry.addViewController("/carType").setViewName("carType/carType");
    // 汽车搜索
    registry.addViewController("/search").setViewName("main/search");
    // 汽车搜索
    registry.addViewController("/carDetail").setViewName("main/carDetail");
    // 用户信息页
    registry.addViewController("/loginLog").setViewName("loginLog");
    // 用户信息页
    registry.addViewController("/userInfo").setViewName("userInfo");
    // 登录页
    registry.addViewController("/login").setViewName("index");
    // 注册页
    registry.addViewController("/reg").setViewName("reg");
    // home页
    registry.addViewController("/loginView").setViewName("home");
    // 选择菜单
    registry.addViewController("/menuCheckView").setViewName("check/menuCheck");
    // 跳转至菜单页
    registry.addViewController("/menuView").setViewName("menu/menu");
    // 跳转是菜单修改
    registry.addViewController("/menuEditView").setViewName("menu/menuPage");
    // 跳转至新增菜单页
    registry.addViewController("/menuAddView").setViewName("menu/menuAdd");
    // 角色选择页面
    registry.addViewController("/roleCheckView").setViewName("check/roleCheck");
    // 角色页
    registry.addViewController("/roleView").setViewName("role/role");
    // 角色修改
    registry.addViewController("/roleEditView").setViewName("role/rolePage");
    // 角色添加
    registry.addViewController("/roleAddView").setViewName("role/roleAdd");
    // 用户页
    registry.addViewController("/userView").setViewName("user/user");
    // 用户修改
    registry.addViewController("/userEditView").setViewName("user/userPage");
    // 用户添加
    registry.addViewController("/userAddView").setViewName("user/userAdd");
  }
}
