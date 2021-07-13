package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.CarInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper
 *
 * @author zyt
 * @date 2021-04-09
 */

@Mapper
public interface CarInfoMapper extends BaseMapper<CarInfo> {

}
