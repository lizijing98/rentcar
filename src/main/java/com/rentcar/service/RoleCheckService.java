package com.rentcar.service;

import com.rentcar.util.Meg;

/**
 * @author lzj
 */
public interface RoleCheckService {
    Meg getRoleCheck(Integer userId);

    Meg roleCheck(Integer userId, Integer[] roleId, Integer type);
}
