package com.yimin.carlayui;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimin.carlayui.common.MarkVo;
import com.yimin.carlayui.common.Statistics;
import com.yimin.carlayui.entity.Order;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.mapper.MarkMapper;
import com.yimin.carlayui.mapper.OrderMapper;
import com.yimin.carlayui.mapper.UserMapper;
import com.yimin.carlayui.service.OrderService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarLayuiApplicationTests {

    @Test
    void contextLoads() {
        final Logger logger = LoggerFactory.getLogger(CarLayuiApplicationTests.class);
        // 5个级别
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }


    @Autowired
    UserMapper userMapper;
    @Test
    void testDeleted(){
        userMapper.deleteById(8);
    }


    @Autowired
    private OrderService orderService;

    @Test
    void testGetEffectiveOrder(){
        Order order = orderService.getEffectiveOrder(35L);
        System.out.println(order);
    }


    @Autowired
    private MarkMapper markMapper;
    @Test
    void testSelectMarkInfo(){
        List<MarkVo> markList = markMapper.selectMarkInfo(0L);
        System.out.println(markList.toString());
    }




    @Test
    void testGetTotalNum(){
        List<Statistics> statisticsList = orderService.getTotalNum();
        System.out.println(statisticsList);
    }


}
