package com.rentcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> 评价状态枚举 </p>
 *
 * @author LiZijing
 * @date 2022/5/2
 */
@Getter
@AllArgsConstructor
public enum AssessStatus {
	// 评价正常显示
	SHOW(0),
	// 还车流程处理中
	PROCESSING(1),
	//
	HIDE(2);
	public final Integer code;
}
