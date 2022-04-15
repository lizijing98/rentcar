package com.rentcar.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Result<T> {
  private int code;
  private String msg;
  private T data;

  public static Result<Void> success() {
    Result<Void> objectResult = new Result<>();
    objectResult.setCode(200);
    objectResult.setMsg("success");
    return objectResult;
  }

  public static <T> Result<T> success(T t) {
    Result<T> result = new Result<>();
    result.setCode(200);
    result.setMsg("success");
    result.setData(t);
    return result;
  }

  public static <T> Result<T> fail(T t) {
    Result<T> result = new Result<>();
    result.setCode(100);
    result.setMsg("fail");
    result.setData(t);
    return result;
  }
}
