package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Role;

/**
 * @author lzj
 */
public interface RoleService extends IService<Role> {

    void action(Integer id, String roleActivate);
}
