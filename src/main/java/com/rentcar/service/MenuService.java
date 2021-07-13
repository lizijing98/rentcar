package com.rentcar.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Menu;

/**
 * @author zyt
 */
public interface MenuService extends IService<Menu> {

    void action(Integer id, String menuActivate);
}
