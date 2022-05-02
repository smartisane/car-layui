package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yimin.carlayui.entity.Order;

public interface OrderService extends IService<Order> {
    //查询当前有效的订单，如果不为null则不可出租
    Order getEffectiveOrder(Long carId);
}
