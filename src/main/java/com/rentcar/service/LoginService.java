package com.rentcar.service;

import com.rentcar.util.Meg;

import javax.servlet.http.HttpSession;

/**
 * @author lzj
 */
public interface LoginService {
	/**
	 * 登录
	 *
	 * @param authCode
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	Meg login(String username, String password, String authCode, String ip);

	/**
	 * 主页json
	 *
	 * @param httpSession
	 * @return
	 */
	Meg homeJson(HttpSession httpSession);

	/**
	 * 登录
	 *
	 * @param authCode
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	Meg loginCustomer(String username, String password, String authCode, String ip);
}
