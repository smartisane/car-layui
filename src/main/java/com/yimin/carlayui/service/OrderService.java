package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yimin.carlayui.common.Statistics;
import com.yimin.carlayui.entity.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    //查询当前有效的订单，如果不为null则不可出租
    Order getEffectiveOrder(Long carId);

    List<Statistics> getTotalNum();
}
