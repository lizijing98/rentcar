package com.rentcar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Carousel;
import com.rentcar.mapper.CarouselMapper;
import com.rentcar.service.CarouselService;
import org.springframework.stereotype.Service;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-27
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
}