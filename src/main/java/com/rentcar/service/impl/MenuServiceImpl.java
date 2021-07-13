package com.rentcar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Menu;
import com.rentcar.mapper.MenuMapper;
import com.rentcar.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author zyt
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public void action(Integer id, String menuActivate) {

    }
}
