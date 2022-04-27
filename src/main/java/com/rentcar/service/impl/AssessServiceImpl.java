package com.rentcar.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.enums.OrderStatus;
import com.rentcar.bean.Assess;
import com.rentcar.bean.Check;
import com.rentcar.bean.Order;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.AssessMapper;
import com.rentcar.mapper.CheckMapper;
import com.rentcar.service.AssessService;
import com.rentcar.service.CheckService;
import com.rentcar.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Assess Service 接口实现
 *
 * @author LiZijing
 * @date 2022/4/17
 */
@Service
@Slf4j
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements AssessService {
  @Resource private OrderService orderService;
  @Resource private CheckService checkService;
  @Resource private CheckMapper checkMapper;
  @Resource private AssessMapper assessMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean initAssess(String orderNum, Serializable customerId, String remake) {
    Order order = orderService.getOneByOrderNum(orderNum);
    if (!order.getCustomerId().equals(customerId)) {
      throw new BusinessException("顾客身份不符");
    }
    Assess assess = BeanUtil.copyProperties(order, Assess.class, "id", "fversion", "state");
    assess.setState(0);
    assess.setRemark(remake);
    return this.save(assess);
  }

  @Override
  public List<Assess> getAssessByCarId(String id) {
    return assessMapper.getAssessByCarId(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void init(Assess assess) {
    Order order = orderService.getOneByOrderNum(assess.getOrderNumber());
    Integer oldAssessId = assessMapper.getIdByOrderNum(assess.getOrderNumber());
    if (oldAssessId != null) {
      assess.setId(oldAssessId);
    }
    //	保存用户评价
    assess.setCustomerId(order.getCustomerId()).setCarInfoId(order.getCarInfoId()).setState(1);
    // 修改订单状态为复检中
    orderService.updateState(order.getId(), OrderStatus.ORD_STA_CHECKING.getCode(), null);
    // 创建检查单
    Check check = checkService.getOneByOrderId(order.getId());
    if (check != null) {
      check.setQuestion("暂未检查").setRemark("null").setState(0);
    } else {
      check =
          new Check()
              .setOrderNumber(order.getOrderNumber())
              .setOrderId(order.getId())
              .setQuestion("暂未检查")
              .setRemark("null")
              .setState(0);
    }
    checkService.save(check);
    this.saveOrUpdate(assess);
  }

  @Override
  public Boolean setStatus(String orderNum, Integer status) {
    Assess assess = assessMapper.getAssessByOrdNum(orderNum);
    if (assess == null) {
		Order order=orderService.getOneByOrderNum(orderNum);
		assess=new Assess()
				.setOrderNumber(orderNum)
				.setCarInfoId(order.getCarInfoId())
				.setCustomerId(order.getCustomerId());
    }
    assess.setState(status);
    return this.updateById(assess);
  }
}
