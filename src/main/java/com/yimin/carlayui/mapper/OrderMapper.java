package com.yimin.carlayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimin.carlayui.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
