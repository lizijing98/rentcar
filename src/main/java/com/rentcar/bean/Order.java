package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lzj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("car_order")
public class Order extends BaseEntity {
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private Integer customerId;

  private String customerIdNum;

  private Integer carInfoId;

  @TableField(exist = false)
  private String customerName;

  @TableField(exist = false)
  private String carPlateNumber;

  private String orderNumber;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private LocalDateTime startDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private LocalDateTime endDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private LocalDateTime outDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private LocalDateTime inDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal money;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal fine;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal penalty;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal total;

  private String feedback;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal cashPledge;

  private Double tenancyTerm;
  /** 状态: 1 申请借车, 2 借车成功, 3 借车失败, 4 申请还车, 5 拒绝还车, 6 同意还车订单完成, 7 事故处理, 8 订单取消, 9 车辆复检完成, 10 复检中, */
  private Integer state;
}
