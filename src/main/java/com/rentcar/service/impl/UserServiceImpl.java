package com.rentcar.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.User;
import com.rentcar.mapper.UserMapper;
import com.rentcar.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zyt
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean save(User entity) {
        Integer count = super.getBaseMapper().selectByName(entity.getUsername());
        if (count > 0) {
            throw new RuntimeException("该用户已存在");
        }
        return UserService.super.save(entity);
    }


    @Override
    public void action(Integer id, String userActivate) {
        this.getBaseMapper().action(id, userActivate);
    }
}
