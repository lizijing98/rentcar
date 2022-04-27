package com.rentcar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.enums.OrderStatus;
import com.rentcar.bean.Check;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.CheckMapper;
import com.rentcar.service.CheckService;
import com.rentcar.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lzj
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements CheckService {

  @Resource private OrderService orderService;
  @Resource private CheckMapper checkMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void init(Check check) {
    boolean save = this.save(check);
    if (save) {
      orderService.updateState(check.getOrderId(), 9, null);
    } else {
      throw new BusinessException("提交检查单失败");
    }
  }

  @Override
  public boolean accident(Check check) {
    check.setRemark("用户提交:" + check.getQuestion());
    if (this.save(check)) {
      orderService.updateState(check.getOrderId(), 7, "提交事故");
      return true;
    } else {
      throw new BusinessException("提交事故检查单失败");
    }
  }

  @Override
  public Check getOneByOrderId(Integer orderId) {
    return checkMapper.getOneByOrderId(orderId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean changeState(Integer id, Integer state) {
    Check check = this.getById(id);
    if (state == 1) {
      checkMapper.updateState(id, state);
      orderService.updateState(check.getOrderId(), OrderStatus.ORD_STA_RECHECK.getCode(), "已复查");
      return true;
    }
    return false;
  }
}
