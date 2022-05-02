package com.rentcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author LiZijing
 * @date 2022/4/25
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
	// 0:初始化
	INIT(0),
	// 1:申请借车
	APPLY_BORROW(1),
	// 2:借车成功 订单进行中
	PROGRESSING(2),
	// 3:借车失败
	FAILED_BORROW(3),
	// 4:申请还车
	APPLY_RETURN(4),
	// 5:拒绝还车
	REFUSE_RETURN(5),
	// 6:同意还车，订单完成
	FINISH(6),
	// 7:事故处理
	ACCIDENT(7),
	// 8:订单取消
	CANCELED(8),
	// 9:车辆复检
	RECHECK(9),
	// 10.复检中
	CHECKING(10);

	public final Integer code;
}
