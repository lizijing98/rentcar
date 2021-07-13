package com.rentcar.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Menu;
import com.rentcar.service.MenuService;
import com.rentcar.util.Meg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zyt
 */
@Controller
public class MenuServlet {

    @Autowired
    private MenuService menuService;

    /**
     * 返回菜单的分页之后的表格数据
     */
    @ResponseBody
    @RequestMapping("/menuData")
    public Meg menuData(@Param("page") Integer page, @Param("limit") Integer limit) {
        Page<Menu> page1 = new Page<>(page, limit);
        return Meg.success().add("data", menuService.page(page1));
    }

    /**
     * 添加菜单的数据和操作后的返回值
     */
    @ResponseBody
    @RequestMapping("/menuInsert")
    public Meg menuInsert(Menu menu) {
        boolean i = menuService.save(menu);
        return i ? Meg.success() : Meg.file();
    }

    /**
     * 修改菜单的处理
     */
    @ResponseBody
    @RequestMapping("/menuUpdate")
    public Meg menuUpdate(Menu menu) {
        System.out.println(menu.getMenuName());
        boolean i = menuService.updateById(menu);
        return i ? Meg.success() : Meg.file();
    }

    /**
     * 删除菜单
     */
    @ResponseBody
    @RequestMapping("/menuDelete")
    public Meg menuDelete(@Param("id") Integer id) {
        boolean i = menuService.removeById(id);
        return i ? Meg.success() : Meg.file();
    }

    /**
     * 对菜单进行激活和禁用
     */
    @ResponseBody
    @RequestMapping("/menuActivate")
    public Meg menuAction(@Param("id") Integer id, @Param("menuActivate") String menuActivate) {
        menuService.action(id, menuActivate);
        return Meg.success();
    }
}
