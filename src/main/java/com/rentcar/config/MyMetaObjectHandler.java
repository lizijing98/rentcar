package com.rentcar.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	private HttpSession httpSession;

	@Override
	public void insertFill(MetaObject metaObject) {
		int userId = 10086;
		Object creatorId = httpSession.getAttribute("userId");
		if (creatorId != null) {
			userId = Integer.parseInt(creatorId.toString());
		}

		this.strictInsertFill(metaObject, "creatorId", Integer.class, userId);
		// 起始版本 3.3.0(推荐使用)
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		int userId = 10086;
		Object creatorId = httpSession.getAttribute("userId");
		if (creatorId != null) {
			userId = Integer.parseInt(creatorId.toString());
		}
		this.strictUpdateFill(metaObject, "modifyId", Integer.class, userId);
		// 起始版本 3.3.0(推荐)
	}

	@Autowired
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
}
