package com.rentcar.service.impl;


import com.rentcar.bean.Menu;
import com.rentcar.bean.MenuRole;
import com.rentcar.mapper.MenuMapper;
import com.rentcar.mapper.MenuRoleMapper;
import com.rentcar.service.MenuCheckService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzj
 */
@Service
public class MenuCheckServiceImpl implements MenuCheckService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    public List getMenuJsonDate(Integer id) {
        List menuIdList = menuRoleMapper.selectByRoleId(id);
        return getMenuJson(0, menuIdList);
    }

    @Override
    public Meg menuCheck(Integer roleId, Integer[] menuId) {
        menuRoleMapper.deleteByRoleId(roleId);
        for (int i = 0; i < menuId.length; i++) {
            MenuRole menuRole = new MenuRole();
            menuRole.setMenuId(menuId[i]);
            menuRole.setRoleId(roleId);
            menuRoleMapper.insert(menuRole);
        }
        return Meg.success();
    }

    public List getMenuJson(Integer id, List menuIdList) {
        List list = new ArrayList();
        List<Menu> menuList = menuMapper.getMenuByParentId(id);
        Map map = null;
        for (int i = 0; i < menuList.size(); i++) {
            map = new HashMap();
            map.put("id", menuList.get(i).getId());
            map.put("title", menuList.get(i).getMenuName());
            map.put("disabled", menuList.get(i).getMenuActivate().equals("on") ? false : true);
            map.put("checked", menuIdList.contains(menuList.get(i).getId()) ? true : false);
            if (getMenuJson(menuList.get(i).getId(), menuIdList).size() > 0) {
                map.put("children", getMenuJson(menuList.get(i).getId(), menuIdList));
            }
            list.add(map);
        }
        return list;
    }


}
