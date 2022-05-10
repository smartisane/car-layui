package com.yimin.carlayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimin.carlayui.common.Statistics;
import com.yimin.carlayui.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select car.type type,SUM(car_order.total) total from car_order,car where car_order.status=1 group by car.type")
    List<Statistics> totalNum();
}
