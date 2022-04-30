package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Customer;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * searchFrom
 *
 * @author lzj
 * @date 2021-04-28
 */
@Data
public class CustomerSearchFrom {
	private Integer pageSize;
	private Integer pageNum;
	private String username;
	private String email;
	private String phone;

	public Page<Customer> getPage() {
		return new Page<>(pageNum, pageSize);
	}

	public QueryWrapper<Customer> queryWrapper() {
		QueryWrapper<Customer> queryWrapper = new QueryWrapper();
		queryWrapper.eq("deleted", "0");
		queryWrapper.like(!StringUtils.isEmpty(username), "username", username);
		queryWrapper.like(!StringUtils.isEmpty(email), "email", email);
		queryWrapper.like(!StringUtils.isEmpty(phone), "phone", phone);
		return queryWrapper;
	}
}
