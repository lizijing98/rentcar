package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Check;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 检修单搜索 VO
 *
 * @author LiZijing
 * @date 2022/4/25
 */
@Data
public class CheckSearchFrom {
  private Integer pageSize;
  private Integer pageNum;
  private String orderNumber;
  private String customerName;
  private String carPlateNumber;

  public Page<Check> getPage() {
    return new Page<>(pageNum, pageSize);
  }

  public QueryWrapper<Check> queryWrapper() {
    QueryWrapper<Check> queryWrapper = new QueryWrapper();
    queryWrapper.eq("deleted", "0");
    queryWrapper.like(!StringUtils.isEmpty(orderNumber), "order_number", orderNumber);
    queryWrapper.like(!StringUtils.isEmpty(customerName), "customer_name", customerName);
    queryWrapper.like(!StringUtils.isEmpty(carPlateNumber), "car_plate_number", carPlateNumber);
    return queryWrapper;
  }
}
