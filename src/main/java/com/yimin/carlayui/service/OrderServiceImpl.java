package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimin.carlayui.common.OrderStatus;
import com.yimin.carlayui.common.Statistics;
import com.yimin.carlayui.entity.Order;
import com.yimin.carlayui.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getEffectiveOrder(Long carId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("car_id",carId);
        wrapper.eq("status", OrderStatus.NORMAL.getValue());
        return orderMapper.selectOne(wrapper);
    }

    @Override
    public List<Statistics> getTotalNum() {
        return orderMapper.totalNum();
    }
}
