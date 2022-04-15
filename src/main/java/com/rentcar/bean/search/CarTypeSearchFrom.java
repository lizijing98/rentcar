package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.CarType;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Author: lzj
 */
@Data
public class CarTypeSearchFrom {
    private Integer pageSize;
    private Integer pageNum;
    private String name;

    public Page<CarType> getPage() {
        return new Page<>(pageNum, pageSize);
    }

    public QueryWrapper<CarType> queryWrapper() {
        QueryWrapper<CarType> queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted", "0");
        queryWrapper.like(!StringUtils.isEmpty(name), "name", name);
        return queryWrapper;
    }
}
