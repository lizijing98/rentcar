package com.rentcar.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.*;
import com.rentcar.bean.VO.InitOrderVO;
import com.rentcar.enums.AssessStatus;
import com.rentcar.enums.CarStatus;
import com.rentcar.enums.CheckStatus;
import com.rentcar.enums.OrderStatus;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.OrderMapper;
import com.rentcar.service.*;
import com.rentcar.util.Meg;
import com.rentcar.util.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * service
 *
 * @author lzj
 * @date 2021-04-19
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	@Resource
	private CustomerService customerService;
	@Resource
	private CarInfoService carInfoService;
	@Resource
	private OrderUtils orderUtils;
	@Resource
	private CheckService checkService;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private AssessService assessService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean initOrder(InitOrderVO initOrderVO, Serializable customerId) {
		log.info("******初始化订单******");
		log.info("orderInfo:{},customerId:{}", initOrderVO.toString(), customerId);
		Customer customer = customerService.getById(customerId);
		CarInfo carInfo = carInfoService.getById(initOrderVO.getCarInfoId());
		log.info("carInfo:{}, customer:{}", carInfo, customer);
		// 客户的余额小于押金
		if (customer.getMoney().compareTo(carInfo.getMoney()) <= 0) {
			throw new BusinessException("余额不足，不能租赁");
		}
		if (CarStatus.RENTED.status.equals(carInfo.getStatus())) {
			throw new BusinessException("该汽车已经被出租");
		}
		Date startDate = DateUtil.parse(initOrderVO.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
		Date endDate = DateUtil.parse(initOrderVO.getFinishDate(), "yyyy-MM-dd HH:mm:ss");
		if (endDate.before(startDate)) {
			throw new BusinessException("结束时间不能早于开始时间");
		}
		// 定期
		Double day = DateUtil.between(startDate, endDate, DateUnit.HOUR) / 24.0;
		BigDecimal money = NumberUtil.mul(day, carInfo.getMoney());
		Order order =
				new Order()
						.setCarInfoId(carInfo.getId())
						.setCustomerId(customer.getId())
						.setStartDate(LocalDateTimeUtil.of(startDate))
						.setEndDate(LocalDateTimeUtil.of(endDate))
						.setCashPledge(carInfo.getCashPledge())
						.setMoney(money)
						.setTenancyTerm(day)
						.setCustomerIdNum(initOrderVO.getIdNumber())
						.setState(1);
		// 生成订单编号
		order.setOrderNumber(orderUtils.getOrderCode(customer.getId()));
		int i = getBaseMapper().insert(order);
		if (i > 0) {
			carInfo.setStatus(CarStatus.RENTED.status);
			carInfoService.updateById(carInfo);
		}
		return i > 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateState(Integer id, Integer state, String feedback) {
		log.info("处理订单状态 订单id：{}，状态：{}", id, state);
		Order order = getBaseMapper().selectById(id);
		order.setFeedback(feedback);
		// 申请还车
		if (state.equals(OrderStatus.CHECKING.code)) {
			applyReturn(state, order);
		}
		// 申请借车
		if (state.equals(OrderStatus.PROGRESSING.code)
				|| state.equals(OrderStatus.FAILED_BORROW.code)) {
			applyBorrow(state, order);
		}
		// 拒绝还车
		if (state.equals(OrderStatus.REFUSE_RETURN.code)) {
			if (order.getState().equals(OrderStatus.RECHECK.code)) {
				order.setState(state);
				checkService.changeState(order.getOrderNumber(),CheckStatus.TEMPORARY.code);
				this.updateById(order);
			} else {
				throw new BusinessException("订单处理失败！请稍后重试。");
			}
		}
		// 同意还车
		if (state.equals(OrderStatus.FINISH.code)) {
			if (order.getState().equals(OrderStatus.RECHECK.code) || order.getState().equals(OrderStatus.ACCIDENT.getCode())) {
				log.info("*****还车开始*****");
				order.setState(state);
				CarInfo carInfo = carInfoService.getById(order.getCarInfoId());
				// 订单预计结束时间
				LocalDateTime endDate = order.getEndDate();
				// 车辆出库时间
				LocalDateTime outDate = order.getOutDate();
				// 车辆入库时间
				LocalDateTime inDate = LocalDateTime.now();
				order.setInDate(inDate);
				// 订单实际结束时间
				order.setEndDate(inDate);
				BigDecimal money = carInfo.getMoney();
				// 正常租期=出库时间 —— 入库时间
				double leaseTerm = LocalDateTimeUtil.between(inDate, outDate, ChronoUnit.HOURS) / 24.0;
				// 不足一天的按一天处理
				if (leaseTerm < 1.0) {
					leaseTerm = 1.0;
				}
				order.setTenancyTerm(leaseTerm);
				// 正常租金
				BigDecimal rent = BigDecimal.valueOf(leaseTerm).multiply(money);
				order.setMoney(rent);
				// 滞纳金 入库时间 > 订单预期结束时间
				BigDecimal penalty = BigDecimal.ZERO;
				double day = 0.0;
				if (inDate.isAfter(endDate)) {
					day = LocalDateTimeUtil.between(endDate, inDate, ChronoUnit.HOURS) / 24.0 + 1.0;
					// 2倍的日租金罚金
					penalty = money.multiply(BigDecimal.valueOf(day)).multiply(BigDecimal.valueOf(2));
				}
				order.setPenalty(penalty);
				// 检修单罚金
				BigDecimal fine = order.getFine();
				// 总金额
				BigDecimal total = rent.add(penalty).add(fine);
				total = total.setScale(2, RoundingMode.UP);
				order.setTotal(total);
				log.info(
						"订单 ID:{},日租金:{},租期:{},逾期:{},正常租金:{},罚金:{},赔付金额:{},总金额：{}",
						id,
						money,
						order.getTenancyTerm(),
						day,
						rent,
						penalty,
						fine,
						total);
				Customer customer = customerService.getById(order.getCustomerId());
				// 减去租金
				customer.setMoney(customer.getMoney().subtract(total).add(customer.getFreeze()));
				customer.setFreeze(BigDecimal.ZERO);
				customerService.updateById(customer);
				if (checkService.getOneByOrderId(order.getId()).getState().equals(CheckStatus.UNCHECK.code)) {
					throw new BusinessException("检查单未处理");
				}
				this.updateById(order);
				// 保存评价
				assessService.setStatus(order.getOrderNumber(), AssessStatus.SHOW.code);
			} else {
				throw new BusinessException("订单处理失败！请稍后重试。");
			}
		}
		// 提交事故
		if (state.equals(OrderStatus.ACCIDENT.code)) {
			if (order.getState().equals(OrderStatus.PROGRESSING.code)) {
				// 检修单罚金
				Check check = checkService.getOneByOrderId(order.getId());
				BigDecimal fine = check.getMoney();
				order.setFine(fine);
				order.setState(state);
				this.updateById(order);
				// 默认评价
				Assess assess=new Assess()
						.setCustomerId(order.getCustomerId())
						.setOrderNumber(order.getOrderNumber())
						.setCarInfoId(order.getCarInfoId())
						.setState(AssessStatus.PROCESSING.code);
				assessService.save(assess);
			} else {
				throw new BusinessException("订单状态异常，无法创建事故单");
			}
		}
		// 取消订单
		if (state.equals(OrderStatus.CANCELED.code)) {
			// 检查订单状态是否为申请借车
			if (order.getState().equals(OrderStatus.APPLY_BORROW.code)) {
				order.setState(state);
				this.updateById(order);
			} else {
				throw new BusinessException("订单状态已改变，不能取消！");
			}
		}
		// 订单完成复查
		if (state.equals(OrderStatus.RECHECK.code)) {
			if (order.getState().equals(OrderStatus.CHECKING.code)
					|| order.getState().equals(OrderStatus.ACCIDENT.code)) {
				// 检修单罚金
				Check check = checkService.getOneByOrderId(order.getId());
				BigDecimal fine = check.getMoney();
				order.setFine(fine);
				order.setState(state);
				this.updateById(order);
			} else {
				throw new BusinessException("订单状态异常");
			}
		}

		// 修改汽车状态
		if (state.equals(OrderStatus.FINISH.code) || state.equals(OrderStatus.CANCELED.code) || state.equals(OrderStatus.FAILED_BORROW.getCode())) {
			// 订单完成、订单取消、借车失败 车辆为未出租
			carInfoService.changeCarStatus(order.getCarInfoId(), CarStatus.NOT_RENTED.status);
		} else if (state.equals(OrderStatus.ACCIDENT.code)) {
			// 提交事故 车辆为检修中
			carInfoService.changeCarStatus(order.getCarInfoId(), CarStatus.REPAIR.status);
		} else if (state.equals(OrderStatus.PROGRESSING.code)) {
			// 订单进行中 车辆为已出租
			carInfoService.changeCarStatus(order.getCarInfoId(), CarStatus.RENTED.status);
		}
		return true;
	}

	@Override
	public Order getOneByOrderNum(String orderNum) {
		return orderMapper.getOneByOrderNum(orderNum);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateMoney(Integer id, BigDecimal money) {
		log.info("修改订单金额: 订单 ID {}, 增加金额 {}", id, money);
		Order order = this.getById(id);
		order.setMoney(order.getMoney().add(money));
		return this.updateById(order);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Meg delay(Integer id, String day) {
		log.info("延长订单时间: ");
		Order order = this.getById(id);
		LocalDateTime oldEndDate = order.getEndDate();
		LocalDateTime newEndDate = LocalDateTimeUtil.parse(day, "yyyy-MM-dd HH:mm:ss");
		if (oldEndDate.isAfter(newEndDate)) {
			return Meg.fail("延期日期不可早于原结束日期");
		}
		if (!order.getState().equals(OrderStatus.PROGRESSING.code)) {
			log.error("订单状态非进行中");
			return Meg.fail("订单不可延期");
		}
		// 修改结束时间
		order.setEndDate(newEndDate);
		// 修改定期
		LocalDateTime beginDate = order.getStartDate();
		double newDay = LocalDateTimeUtil.between(beginDate, newEndDate, ChronoUnit.HOURS) / 24.0 + 1;
		// 修改订单金额
		BigDecimal carMoney = carInfoService.getMoneyById(order.getCarInfoId());
		BigDecimal newMoney = order.getMoney().add(carMoney.multiply(BigDecimal.valueOf(newDay)));
		order.setMoney(newMoney);
		if (this.updateById(order)) {
			return Meg.success();
		}
		return Meg.fail();
	}

	@Override
	public Boolean checkIdNum(Integer id, String idNum) {
		String orderCusIdNum = orderMapper.getCusIdNumById(id);
		return StrUtil.equals(idNum.substring(6), orderCusIdNum);
	}

	@Transactional(rollbackFor = Exception.class)
	protected void applyReturn(Integer state, Order order) {
		if (order.getState().equals(OrderStatus.PROGRESSING.code)) {
			order.setState(state);
			if (!this.updateById(order)) {
				throw new BusinessException("申请还车失败!");
			}
		} else if (order.getState().equals(OrderStatus.REFUSE_RETURN.code)) {
			order.setState(state);
			if (!this.updateById(order)) {
				throw new BusinessException("重新申请还车失败!");
			}
		} else {
			throw new BusinessException("其他异常!");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	protected void applyBorrow(Integer state, Order order) {
		if (order.getState().equals(OrderStatus.APPLY_BORROW.code)) {
			// 预订单转订单
			if (state.equals(OrderStatus.PROGRESSING.code)) {
				order.setOutDate(LocalDateTime.now());
			}
			Customer customer = customerService.getById(order.getCustomerId());
			CarInfo carInfo = carInfoService.getById(order.getCarInfoId());
			// 修改用户余额
			customer.setFreeze(carInfo.getCashPledge());
			customer.setMoney(customer.getMoney().subtract(carInfo.getCashPledge()));
			order.setState(state);
			if (!customerService.updateById(customer)) {
				throw new BusinessException("用户信息处理失败");
			}
			if (!this.updateById(order)) {
				throw new BusinessException("订单处理失败！请稍后重试。");
			}
		} else if (order.getState().equals(OrderStatus.CHECKING.code)) {
			// 取消还车
			order.setState(state);
			if (!this.updateById(order)) {
				throw new BusinessException("取消申请失败!");
			}
		} else {
			throw new BusinessException("其他异常!");
		}
	}
}
