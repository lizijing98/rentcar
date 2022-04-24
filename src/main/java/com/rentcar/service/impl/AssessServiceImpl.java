package com.rentcar.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Assess;
import com.rentcar.bean.Order;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.AssessMapper;
import com.rentcar.service.AssessService;
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
}
