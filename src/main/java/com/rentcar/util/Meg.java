package com.rentcar.util;

import java.util.HashMap;
import java.util.Map;

public class Meg {
  private Integer code;
  private String message;
  private Map<String, Object> map = new HashMap<String, Object>();

  public static Meg success() {
    Meg meg = new Meg();
    meg.code = 200;
    meg.message = "success";
    return meg;
  }

  public static Meg loginFile() {
    Meg meg = new Meg();
    meg.code = 1000;
    meg.message = "登录失败！账户或密码错误！";
    return meg;
  }

  public static Meg loginSuccess() {
    Meg meg = new Meg();
    meg.code = 2000;
    meg.message = "登录成功，即将转往首页！";
    return meg;
  }

  public static Meg success(String message) {
    Meg meg = new Meg();
    meg.code = 200;
    meg.message = message;
    return meg;
  }

  public static Meg fail() {
    Meg meg = new Meg();
    meg.code = 0;
    meg.message = "fail";
    return meg;
  }

  public static Meg fail(String message) {
    Meg meg = new Meg();
    meg.code = 0;
    meg.message = message;
    return meg;
  }

  public static Meg message(int code, String message) {
    Meg meg = new Meg();
    meg.code = code;
    meg.message = message;
    return meg;
  }

  public static Meg userAction() {
    Meg meg = new Meg();
    meg.code = 1000;
    meg.message = "该用户不存在或者被锁定";
    return meg;
  }

  public Meg add(String key, Object value) {
    this.map.put(key, value);
    return this;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }
}
