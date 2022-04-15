package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lzj
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
  /**
   * 根据用户名和密码查询
   *
   * @param username
   * @param password
   * @return
   */
  User selectByUserAndPass(@Param("username") String username, @Param("password") String password);

  /**
   * 查询是否激活
   *
   * @param username
   * @return
   */
  Integer selectAction(String username);

  /**
   * 根据用户名查询
   *
   * @param username
   * @return
   */
  Integer selectByName(String username);

  /**
   * 激活或者停用
   *
   * @param id
   * @param Activate
   * @return
   */
  int action(@Param("id") Integer id, @Param("Activate") String Activate);
}
