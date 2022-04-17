package com.rentcar.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * 日志打印 AOP
 *
 * @author LiZijing
 * @date 2022/4/18
 */
@Aspect
@Component
@Slf4j
public class GlobalLoggingPrint implements Ordered {

  @Pointcut("execution(public * com.rentcar.controller..*.*(..))")
  public void webLog() {}

  @Around("webLog()")
  public Object doAround(ProceedingJoinPoint point) throws Throwable {
    ServletRequestAttributes attr =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = Objects.requireNonNull(attr).getRequest();
    log.info(
        "Request,{},{},{}",
        request.getRequestURI(),
        request.getMethod(),
        Arrays.toString(point.getArgs()));
    long startTime = System.currentTimeMillis();
    Object result = point.proceed();
    if (result != null) {
      log.info(
          "Response,{},{},{},{},{}",
          request.getRequestURI(),
          request.getMethod(),
          Arrays.toString(point.getArgs()),
          result,
          (System.currentTimeMillis() - startTime) + "ms");
    } else {
      log.info(
          "Response,{},{},{},{}",
          request.getRequestURI(),
          request.getMethod(),
          Arrays.toString(point.getArgs()),
          (System.currentTimeMillis() - startTime) + "ms");
    }
    return result;
  }

  @Override
  public int getOrder() {
    return 1;
  }
}
