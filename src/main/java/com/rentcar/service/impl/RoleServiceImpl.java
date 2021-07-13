package com.rentcar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Role;
import com.rentcar.mapper.RoleMapper;
import com.rentcar.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author zyt
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public void action(Integer id, String roleActivate) {
        this.baseMapper.action(id, roleActivate);
    }
}
