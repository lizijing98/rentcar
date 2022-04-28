package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzj
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
	/**
	 * 根据父菜单id获取菜单列表
	 *
	 * @param id
	 * @return
	 */
	List<Menu> getMenuByParentId(Integer id);

	/**
	 * 激活
	 *
	 * @param id
	 * @param activate
	 * @return
	 */
	int action(@Param("id") Integer id, @Param("activate") String activate);
}
