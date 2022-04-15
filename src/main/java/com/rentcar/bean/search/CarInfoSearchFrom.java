package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.CarInfo;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Author: lzj
 */
@Data
public class CarInfoSearchFrom {
  private Integer pageSize;
  private Integer pageNum;
  // 车牌
  private String plateNumber;
  // 制造商
  private String manufacturer;
  // 品牌
  private String brand;

  public Page<CarInfo> getPage() {
    return new Page<>(pageNum, pageSize);
  }

  public QueryWrapper<CarInfo> queryWrapper() {
    QueryWrapper<CarInfo> queryWrapper = new QueryWrapper();
    queryWrapper.eq("info.deleted", "0");
    queryWrapper.like(!StringUtils.isEmpty(plateNumber), "info.plate_number", plateNumber);
    queryWrapper.like(!StringUtils.isEmpty(brand), "info.brand", brand);
    queryWrapper.like(!StringUtils.isEmpty(manufacturer), "info.manufacturer", manufacturer);
    return queryWrapper;
  }
}
