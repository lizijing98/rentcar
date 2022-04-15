package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzj
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id查询
     *
     * @param userId
     * @return
     */
    List<UserRole> selectByUserId(Integer userId);

    /**
     * 根据角色用户查询
     *
     * @param roleId
     * @param userId
     * @return
     */
    UserRole selectByRoleAndUser(@Param("roleId") Integer roleId, @Param("userId") Integer userId);

}