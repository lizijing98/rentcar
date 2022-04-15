package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`check`")
public class Check extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Integer id;

  private Integer orderId;

  private String orderNumber;

  private String question;

  private String remark;

  private BigDecimal money;
}
