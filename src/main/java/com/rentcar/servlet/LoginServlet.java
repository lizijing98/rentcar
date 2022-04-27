package com.rentcar.servlet;

import com.rentcar.bean.User;
import com.rentcar.service.LoginService;
import com.rentcar.util.IpUtil;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 * @Author lzj
 * @Description 这是一个登陆的控制器类
 * @Date 9:41 2020/1/9
 * @Param
 * @return
 **/
@Controller
public class LoginServlet {
  /** 自动注入登陆的逻辑层的实例 */
  @Autowired private LoginService loginService;

  @Autowired private HttpSession httpSession;

  /** 登陆时ajax传的数据的的处理 */
  @RequestMapping("/loginData")
  @ResponseBody
  public Meg loginData(
      @RequestParam String username,
      @RequestParam String password,
      @RequestParam String authCode,
      HttpServletRequest request) {
    String ip = IpUtil.getIpAddr(request);
    return loginService.login(username, password, authCode, ip);
  }

  /** 登陆成功后加载主页左侧菜单的处理 */
  @RequestMapping("/loginJson")
  @ResponseBody
  public Meg loginJson() {
    return loginService.homeJson(httpSession);
  }

  /** 退出后清除session,跳转到登陆页面 */
  @RequestMapping("/exit")
  public String exit() {
    httpSession.invalidate();
    return "index";
  }
}
