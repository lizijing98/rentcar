package com.rentcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> 检查单状态枚举 </p>
 *
 * @author LiZijing
 * @date 2022/5/2
 */
@Getter
@AllArgsConstructor
public enum CheckStatus {
	// 未检查
	UNCHECK(0),
	// 检查中
	CHECKING(1),
	// 检查完成
	FINISH(2),
	// 检查单暂存
	TEMPORARY(3);
	public final Integer code;
}
