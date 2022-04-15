package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Order;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * searchFrom
 *
 * @author lzj
 * @date 2021-04-28
 */
@Data
public class OrderSearchFrom {
    private Integer pageSize;
    private Integer pageNum;
    private Integer customerId;
    private String orderNumber;
    private String customerName;
    private String carPlateNumber;

    public Page<Order> getPage() {
        return new Page<>(pageNum, pageSize);
    }

    public QueryWrapper<Order> queryWrapper() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        queryWrapper.eq("car_order.deleted", "0");
        queryWrapper.eq(!StringUtils.isEmpty(customerId), "car_order.customer_id", customerId);
        queryWrapper.like(!StringUtils.isEmpty(orderNumber), "car_order.order_number", orderNumber);
        queryWrapper.like(!StringUtils.isEmpty(customerName), "customer.username", customerName);
        queryWrapper.like(!StringUtils.isEmpty(carPlateNumber), "car_info.plate_number", carPlateNumber);
        return queryWrapper;
    }
}