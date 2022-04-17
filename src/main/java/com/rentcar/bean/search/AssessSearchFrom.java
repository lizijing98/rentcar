package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Assess;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 评价搜索类
 *
 * @author LiZijing
 * @date 2022/4/17
 */
@Data
public class AssessSearchFrom {
	private Integer pageSize;
	private Integer pageNum;
	private Integer customerId;
	private String orderNumber;
	private String customerName;
	private String carPlateNumber;

	public Page<Assess> getPage() {
		return new Page<>(pageNum, pageSize);
	}

	public QueryWrapper<Assess> queryWrapper() {
		QueryWrapper<Assess> queryWrapper = new QueryWrapper();
		queryWrapper.eq("ca.deleted", "0");
		queryWrapper.eq(!StringUtils.isEmpty(customerId), "co.customer_id", customerId);
		queryWrapper.like(!StringUtils.isEmpty(orderNumber), "co.order_number", orderNumber);
		queryWrapper.like(!StringUtils.isEmpty(customerName), "cus.username", customerName);
		queryWrapper.like(!StringUtils.isEmpty(carPlateNumber), "ci.plate_number", carPlateNumber);
		return queryWrapper;
	}
}
