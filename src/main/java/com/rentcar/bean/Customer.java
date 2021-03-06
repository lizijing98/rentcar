package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author lzj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Customer extends BaseEntity {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String username;

	private String password;

	private String email;

	private String phone;

	private String gender;

	private String remark;

	private String activate;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal money;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal freeze;

	private String address;

	private String profession;

	private String idNumber;
}
