package com.rentcar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Notice;
import com.rentcar.mapper.NoticeMapper;
import com.rentcar.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-19
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
}