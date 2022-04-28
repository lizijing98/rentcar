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
	ORD_STA_INIT(0),
	// 1:申请借车
	ORD_STA_APPLY_BORROW(1),
	// 2:借车成功 订单进行中
	ORD_STA_PROGRESSING(2),
	// 3:借车失败
	ORD_STA_FAILED_BORROW(3),
	// 4:申请还车
	ORD_STA_APPLY_RETURN(4),
	// 5:拒绝还车
	ORD_STA_REFUSE_RETURN(5),
	// 6:同意还车，订单完成
	ORD_STA_FINISH(6),
	// 7:事故处理
	ORD_STA_ACCIDENT(7),
	// 8:订单取消
	ORD_STA_CANCELED(8),
	// 9:车辆复检
	ORD_STA_RECHECK(9),
	// 10.复检中
	ORD_STA_CHECKING(10);

	private final Integer code;
}
