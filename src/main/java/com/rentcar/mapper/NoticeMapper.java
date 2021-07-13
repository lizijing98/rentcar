package com.rentcar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rentcar.bean.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper
 *
 * @author zyt
 * @date 2021-04-19
 */


@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
