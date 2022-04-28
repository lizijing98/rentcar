package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lzj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String menuName;

	private Integer menuLevel;

	private Integer menuParentId;

	private String menuUrl;

	private String menuActivate;

	private String remark;
}
