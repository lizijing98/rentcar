package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper
 *
 * @author lzj
 * @date 2021-04-19
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}
