package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.User;

/**
 * @author zyt
 */
public interface UserService extends IService<User> {
    /**
     * 激活
     *
     * @param id
     * @param userActivate
     */
    void action(Integer id, String userActivate);
}
