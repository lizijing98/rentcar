package com.rentcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> 车辆状态枚举 </p>
 *
 * @author LiZijing
 * @date 2022/5/2
 */
@Getter
@AllArgsConstructor
public enum CarStatus {
	// 未出租
	NOT_RENTED("未出租"),
	// 已出租
	RENTED("已出租"),
	// 检修中
	REPAIR("检修中");
	public final String status;
}
