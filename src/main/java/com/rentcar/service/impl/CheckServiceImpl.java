package com.rentcar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rentcar.bean.Check;
import com.rentcar.exception.BusinessException;
import com.rentcar.mapper.CheckMapper;
import com.rentcar.service.CheckService;
import com.rentcar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zyt
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements CheckService {

    private OrderService orderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void init(Check check) {
        boolean save = this.save(check);
        if (save){
            orderService.updateState(check.getOrderId(), 4, null);
        } else {
            throw new BusinessException("提交检查单失败");
        }
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
