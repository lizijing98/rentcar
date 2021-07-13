package com.rentcar.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.UpdatePass;
import com.rentcar.bean.User;
import com.rentcar.service.UserService;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author zyt
 */
@RestController
@Slf4j
public class UserServlet {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/userDate")
    public Meg userData(@Param("page") Integer page, @Param("limit") Integer limit) {
        Page<User> page1 = new Page<>(page, limit);
        return Meg.success().add("data", userService.page(page1));
    }

    /**
     * 添加用户
     */
    @RequestMapping("/userInsert")
    public Meg userInsert(User user) {
        return userService.save(user) ? Meg.success() : Meg.file();
    }

    /**
     * 添加用户
     */
    @RequestMapping("/regUser")
    public Meg reg(User user, @RequestParam String authCode) {
        authCode = authCode.toLowerCase();
        boolean bool = authCode.equals(httpSession.getAttribute("authCode"));
        if (!bool) {
            return Meg.file("验证码错误");
        }
        user.setActivate("on");
        return userService.save(user) ? Meg.success() : Meg.file();
    }

    /**
     * 更新用户
     */
    @RequestMapping("/userUpdate")
    public Meg userUpdate(User user) {
        if (user.getActivate() == null) {
            user.setActivate("off");
        }
        boolean i = userService.updateById(user);
        return i ? Meg.success() : Meg.file();
    }

    /**
     * 删除用户
     */
    @RequestMapping("/userDelete")
    public Meg userDelete(@Param("id") Integer id) {
        boolean i = userService.removeById(id);
        return i ? Meg.success() : Meg.file();
    }

    @RequestMapping("/userActivate")
    public Meg menuAction(@Param("id") Integer id, @Param("userActivate") String userActivate) {
        userService.action(id, userActivate);
        return Meg.success();
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo")
    public Meg getUserInfo(@SessionAttribute("userId") Integer userId) {
        //从数据库查询该用户
        User user = userService.getById(userId);
        return Meg.success().add("data", user);
    }

    /**
     * 更改密码
     */
    @PostMapping("/updatePassword")
    public Meg updatePassword(@RequestBody UpdatePass updatePass) {
        log.info("修改密码===>{}", updatePass);
        String oldPassword = updatePass.getOldPassword();
        String newPassword = updatePass.getNewPassword();
        String newPassword2 = updatePass.getNewPassword2();
        User user = (User) httpSession.getAttribute("user");
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println(user);
            return Meg.file("密码错误");
        }
        if (newPassword.equals(newPassword2)) {
            user.setPassword(newPassword);
            userService.updateById(user);
            httpSession.setAttribute("user", user);
            return Meg.success();
        } else {
            return Meg.file("密码不一致");
        }
    }

    /**
     * 更新个人信息
     */
    @PostMapping("/updateInfo")
    public Meg updateMyInfo(@RequestBody User user) {
        log.debug("修改用户信息，{}", user);
        User user1 = userService.getById(user.getId());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setGender(user.getGender());
        userService.updateById(user1);
        httpSession.setAttribute("user", user1);
        return Meg.success();
    }
}
