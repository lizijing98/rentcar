package com.rentcar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rentcar.bean.Check;

public interface CheckService extends IService<Check> {

    void init(Check check);
}
