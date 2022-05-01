package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p> 评价实体 </p>
 *
 * @author LiZijing
 * @date 2022/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("car_assess")
public class Assess extends BaseEntity {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private Integer customerId;

	private Integer carInfoId;

	@TableField(exist = false)
	private String customerName;

	@TableField(exist = false)
	private String carPlateNumber;

	private String orderNumber;

	private String remark;

	/**
	 * 订单状态：
	 * 0.正常显示
	 * 1.还车流程未完成
	 * 2.暂不显示
	 */
	private Integer state;
}
