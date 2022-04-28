package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Carousel;
import com.rentcar.service.CarouselService;
import com.rentcar.util.Meg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * controller 控制器
 *
 * @author lzj
 * @date 2021-04-27
 */
@RestController
@RequestMapping("/api/carousel")
public class CarouselController {
	private final CarouselService service;

	@Autowired
	public CarouselController(CarouselService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public Meg getById(@PathVariable Integer id) {
		final Carousel bean = service.getById(id);
		return Meg.success().add("data", bean);
	}

	@PutMapping("/{id}")
	public Meg update(@RequestBody Carousel bean) {
		Boolean bool = service.updateById(bean);
		return bool ? Meg.success() : Meg.fail();
	}

	@PostMapping("/page")
	public Meg page(Integer pageSize, Integer pageNum) {
		final Page<Carousel> page = service.page(new Page<>(pageNum, pageSize));
		return Meg.success().add("data", page);
	}

	@DeleteMapping("/{id}")
	public Meg del(@PathVariable Integer id) {
		Boolean bool = service.removeById(id);
		return bool ? Meg.success() : Meg.fail();
	}

	@PostMapping
	public Meg insert(@RequestBody Carousel bean) {
		Boolean bool = service.save(bean);
		return bool ? Meg.success() : Meg.fail();
	}
}
