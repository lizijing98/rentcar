package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: lzj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CarInfo extends BaseEntity {
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private Integer carType;

  @TableField(exist = false)
  private String carTypeName;

  private BigDecimal money;
  private String status;
  @NotNull private String plateNumber;
  private String carColor;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date producedDate;

  private String remark;
  // 图片
  private String image;
  // 品牌
  private String brand;
  // 押金
  private BigDecimal cashPledge;
  // 制造商
  private String manufacturer;
}
