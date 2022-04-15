package com.rentcar.exception;

import com.rentcar.util.Meg;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 异常处理Handler，错误处理从子到父匹配
 *
 * @author lzj
 */
@ControllerAdvice
public class ApiExceptionHandler {

  /**
   * 处理系统发生的任意错误时的处理
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Meg error(Exception e) {
    e.printStackTrace();
    return Meg.file(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public Meg methodArgumentNotValidException(MethodArgumentNotValidException c) {
    List<ObjectError> errors = c.getBindingResult().getAllErrors();
    StringBuffer errorMsg = new StringBuffer();
    errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
    return Meg.file(errorMsg.toString());
  }

  /**
   * 业务异常处理器
   *
   * @param e
   * @return
   */
  @ExceptionHandler(BusinessException.class)
  @ResponseBody
  public Meg businessException(BusinessException e) {
    return Meg.file(e.getMessage());
  }
}
