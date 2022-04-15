package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.MenuRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzj
 */
@Repository
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
  /**
   * 根据角色删除
   *
   * @param roleId
   * @return
   */
  int deleteByRoleId(Integer roleId);

  /**
   * 根据角色查询
   *
   * @param roleId
   * @return
   */
  List<Integer> selectByRoleId(Integer roleId);
}
