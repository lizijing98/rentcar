package com.rentcar.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 延期处理单
 *
 * @author LiZijing
 * @date 2022/4/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Delay extends BaseEntity {
	private Integer orderId;
	private String orderNumber;
	private String day;
}
