package com.rentcar.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzj
 */
@Data
@NoArgsConstructor
public class Meg {
  private Integer code;
  private String message;
  private Map<String, Object> map = new HashMap<>();

  public Meg(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public static Meg success() {
    return new Meg(200, "success");
  }

  public static Meg loginFile() {
    return new Meg(1000, "登录失败！账户或密码错误！");
  }

  public static Meg loginSuccess() {
    return new Meg(2000, "登录成功，即将转往首页！");
  }

  public static Meg success(String message) {
    return new Meg(200, message);
  }

  public static Meg fail() {
    return new Meg(0, "fail");
  }

  public static Meg fail(String message) {
    return new Meg(0, message);
  }

  public static Meg message(int code, String message) {
    return new Meg(code, message);
  }

  public static Meg userAction() {
    return new Meg(1000, "该用户不存在或者被锁定");
  }

  public static Meg noLogin() {
    return new Meg(403, "请先登录!");
  }

  public Meg add(String key, Object value) {
    this.map.put(key, value);
    return this;
  }
}
