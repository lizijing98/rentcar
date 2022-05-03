package com.rentcar.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.CarInfo;
import com.rentcar.bean.CarType;
import com.rentcar.bean.Customer;
import com.rentcar.bean.Notice;
import com.rentcar.service.*;
import com.rentcar.util.IpUtil;
import com.rentcar.util.Meg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lzj
 */
@Slf4j
@Controller
public class CustomerViewController {

	@Resource
	private HttpSession httpSession;
	@Resource
	private LoginService loginService;
	@Resource
	private CarTypeService carTypeService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CarInfoService carInfoService;
	@Resource
	private CarouselService carouselService;
	@Resource
	private AssessService assessService;

	@GetMapping("/")
	public ModelAndView main() {
		ModelAndView model = new ModelAndView("main");
		final List<CarType> types = carTypeService.listAndInfoList();
		final List<Notice> notices = noticeService.list();
		model.addObject("types", types);
		model.addObject("notices", notices);
		model.addObject("carousels", carouselService.list());
		return model;
	}

	@GetMapping("/main/type")
	public ModelAndView typeList(
			ModelAndView mv,
			@RequestParam(value = "carTypeId", defaultValue = "1") Integer carTypeId,
			@RequestParam(defaultValue = "1") Integer pageNum) {
		log.info("分类页面:typeId:{},pageNum:{}", carTypeId, pageNum);
		mv.setViewName("main/carType");
		QueryWrapper<CarInfo> qw = new QueryWrapper<>();
		qw.eq("car_type", carTypeId);
		qw.eq("info.deleted", "0");
		qw.eq("type.deleted", "0");
		Page<CarInfo> page = carInfoService.page(new Page<>(pageNum, 8), qw);
		mv.addObject("type", carTypeService.getById(carTypeId));
		mv.addObject("notices", noticeService.list());
		mv.addObject("page", page);
		mv.addObject("types", carTypeService.list());
		mv.addObject("carousels", carouselService.list());
		return mv;
	}

	@GetMapping("/main/search")
	public ModelAndView search(ModelAndView mv) {
		mv.setViewName("main/search");
		return mv;
	}

	@GetMapping("/customerLogin")
	public String login() {
		httpSession.invalidate();
		return "customer/customerLogin";
	}

	@PostMapping("/customerLogin")
	@ResponseBody
	public Meg customerLogin(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String authCode,
			HttpServletRequest request) {
		String ip = IpUtil.getIpAddr(request);
		return loginService.loginCustomer(username, password, authCode, ip);
	}

	@GetMapping("/customer/exit")
	public String exit() {
		httpSession.invalidate();
		return "customer/customerLogin";
	}

	@GetMapping("/currentCustomer")
	public Meg currentCustomer(HttpSession httpSession) {
		Object customerIdObj = httpSession.getAttribute("customerId");
		if (customerIdObj == null) {
			Meg meg = new Meg();
			meg.setCode(403);
			return meg;
		}
		Integer customerId = Integer.parseInt(customerIdObj + "");
		Customer customer = customerService.getById(customerId);
		customer.setPassword("");
		return Meg.success().add("data", customer);
	}

	@GetMapping("/customerInfo")
	public ModelAndView customerInfo(ModelAndView mv, HttpSession httpSession) {
		final Integer customerId = (Integer) httpSession.getAttribute("customerId");
		mv.setViewName("main/customerInfo");
		mv.addObject("notices", noticeService.list());
		mv.addObject("types", carTypeService.list());
		mv.addObject("info", customerService.getById(customerId));
		return mv;
	}

	@GetMapping("/main/notice/{id}")
	public ModelAndView notice(ModelAndView mv, @PathVariable String id) {
		mv.setViewName("main/notice");
		mv.addObject("types", carTypeService.list());
		mv.addObject("notices", noticeService.list());
		mv.addObject("notice", noticeService.getById(id));
		return mv;
	}

	@GetMapping("/main/carDetail/{id}")
	public ModelAndView carDetail(ModelAndView mv, @PathVariable String id) {
		mv.setViewName("main/carDetail");
		mv.addObject("carInfo", carInfoService.getOneById(id));
		mv.addObject("assesss", assessService.getAssessByCarId(id));
		return mv;
	}

	@GetMapping("/myOrder")
	public ModelAndView myOrder(ModelAndView mv, HttpSession httpSession) {
		final Integer customerId = (Integer) httpSession.getAttribute("customerId");
		mv.setViewName("main/myOrder");
		mv.addObject("id", customerId);
		return mv;
	}

	@GetMapping("/myAssess")
	public ModelAndView myAssess(ModelAndView mv, HttpSession httpSession) {
		final Integer customerId = (Integer) httpSession.getAttribute("customerId");
		mv.setViewName("main/myAssess");
		mv.addObject("id", customerId);
		return mv;
	}

	/**
	 * 添加客户
	 */
	@ResponseBody
	@PostMapping("/customerRegister")
	public Meg reg(Customer customer, @RequestParam String authCode, HttpSession httpSession) {
		if (!authCode.toLowerCase().equals(httpSession.getAttribute("authCode"))) {
			return Meg.fail("验证码错误");
		}
		customer.setActivate("on");
		return customerService.save(customer) ? Meg.success() : Meg.fail();
	}

	@GetMapping("/customerRegister")
	public String register() {
		return "customer/customerRegister";
	}

}
