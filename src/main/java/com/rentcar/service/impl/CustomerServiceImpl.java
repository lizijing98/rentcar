package com.rentcar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Customer;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.CustomerMapper;
import com.rentcar.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-19
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Override
    public boolean save(Customer entity) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", "0");
        queryWrapper.eq("username", entity.getUsername());
        final List<Customer> list = this.list(queryWrapper);
        if (list.size() > 0) {
            throw new BusinessException("该用户名已被使用");
        }
        return super.save(entity);
    }
}