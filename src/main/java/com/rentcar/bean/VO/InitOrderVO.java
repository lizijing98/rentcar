package com.rentcar.bean.VO;

import lombok.Data;

/**
 * 创建订单 VO
 *
 * @author LiZijing
 * @date 2022/4/24
 */
@Data
public class InitOrderVO {

  private Integer carInfoId;
  private String createDate;
  private String finishDate;
  private String idNumber;
}
