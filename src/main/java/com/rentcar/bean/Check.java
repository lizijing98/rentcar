package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author lzj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`check`")
@Accessors(chain = true)
public class Check extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Integer id;

  private Integer orderId;

  private String orderNumber;

  private String question;

  private String remark;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal money;

  @TableField(exist = false)
  private String customerName;

  @TableField(exist = false)
  private String carPlateNumber;

  private Integer state;
}
