package com.rentcar.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 系统常量
 *
 * @author LiZijing
 * @date 2022/4/27
 */
@Configuration
public class SystemConstant implements InitializingBean {
	public static final String CUSTOMER_ID = "customerId";
	public static final String USER_ID = "userId";

	private static final String DOCKER = "docker";

	public static String FILE_PATH = null;

	@Value("${spring.profiles.active}")
	private String env;

	@Override
	public void afterPropertiesSet() {
		if (DOCKER.equals(env)) {
			FILE_PATH = "/static/";
		} else {
			FILE_PATH = "\\static\\";
		}
	}
}
