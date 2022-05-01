package com.rentcar.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Assess;
import com.rentcar.bean.search.AssessSearchFrom;
import com.rentcar.constant.SystemConstant;
import com.rentcar.service.AssessService;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 评价 controller
 *
 * @author LiZijing
 * @date 2022/4/17
 */
@Slf4j
@RestController
@RequestMapping("/api/assess")
public class AssessController {

	@Resource
	private AssessService assessService;

	@GetMapping("/{id}")
	public Meg getById(@PathVariable Integer id) {
		final Assess assess = assessService.getById(id);
		return Meg.success().add("data", assess);
	}

	@PostMapping
	public Meg create(@RequestBody Assess assess) {
		assessService.init(assess);
		return Meg.success();
	}

	@PutMapping("/{id}")
	public Meg update(@RequestBody Assess assess) {
		return assessService.updateById(assess) ? Meg.success() : Meg.fail();
	}

	@PostMapping("/page")
	public Meg page(@RequestBody AssessSearchFrom searchFrom, HttpSession session) {
		if (session.getAttribute(SystemConstant.CUSTOMER_ID) == null
				&& session.getAttribute(SystemConstant.USER_ID) == null) {
			return Meg.noLogin().add("data", null);
		}
		log.info("搜索评价: {}", searchFrom);
		final Page<Assess> page = assessService.page(searchFrom.getPage(), searchFrom.queryWrapper());
		return Meg.success().add("data", page);
	}

	@DeleteMapping("/{id}")
	public Meg del(@PathVariable Integer id) {
		return assessService.removeById(id) ? Meg.success() : Meg.fail();
	}

	@PostMapping("/initAssess")
	public Meg initAssess(
			@RequestParam("orderNum") String orderNum,
			@RequestParam("remark") String remark,
			HttpSession session) {
		Object customerId = session.getAttribute("customerId");
		if (customerId == null) {
			return Meg.noLogin();
		}
		return assessService.initAssess(orderNum, customerId + "", remark) ? Meg.success() : Meg.fail();
	}

	@PostMapping("/{id}/state/{state}")
	public Meg changeState(@PathVariable Integer id, @PathVariable Integer state) {
		return assessService.changeStatus(id, state) ? Meg.success() : Meg.fail();
	}
}
