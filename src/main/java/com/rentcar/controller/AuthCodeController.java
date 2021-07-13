package com.rentcar.controller;

import com.rentcar.util.AuthCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zyt
 * @Description 这个是请求验证码的接口
 * @Date 9:32 2020/1/9
 * @Param
 * @return 返回值是一个字节流（图片）
 **/
@Controller
public class AuthCodeController {
    @RequestMapping("/getAuthCode")
    @ResponseBody
    public void getAuthCode(HttpServletResponse response, HttpServletRequest request) {
        AuthCodeUtils.getVerificationCode(response, request);
    }
}
