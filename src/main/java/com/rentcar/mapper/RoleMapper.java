package com.rentcar.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lzj
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 激活或者停用
     *
     * @param id
     * @param Activate
     * @return
     */
    int action(@Param("id") Integer id, @Param("Activate") String Activate);
}