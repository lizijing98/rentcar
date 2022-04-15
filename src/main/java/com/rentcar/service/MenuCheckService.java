package com.rentcar.service;

import com.rentcar.util.Meg;

import java.util.List;

/**
 * @author lzj
 */
public interface MenuCheckService {
    /**
     * 根据用户id获取menuJson
     *
     * @param id
     * @return
     */
    List getMenuJsonDate(Integer id);

    /**
     * 检查菜单
     *
     * @param roleId
     * @param menuId
     * @return
     */
    Meg menuCheck(Integer roleId, Integer[] menuId);
}
