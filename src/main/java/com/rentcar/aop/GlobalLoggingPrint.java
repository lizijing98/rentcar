package com.rentcar.aop;

import cn.hutool.json.JSONUtil;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSession;
import org.apache.catalina.session.StandardSessionFacade;
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
	public void webLog() {
	}

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
		if (result instanceof Meg) {
			log.info(
					"Response,{},{},{},{},{}",
					request.getRequestURI(),
					request.getMethod(),
					Arrays.toString(point.getArgs()),
					JSONUtil.parse(result),
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
