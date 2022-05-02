package com.yimin.carlayui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimin.carlayui.common.CarStatus;
import com.yimin.carlayui.entity.Car;
import com.yimin.carlayui.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 进入首页，携带查询的信息展示
 */
@Controller
public class IndexController {

    @Autowired
    private CarService carService;

    @GetMapping(value = {"/","/index"})
    public String index(Model model){

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("status", CarStatus.NOT_RENT);
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 6");//查询6条记录
        List<Car> carList = carService.list(wrapper);
        model.addAttribute("carList",carList);

        //首页时选中选项
        model.addAttribute("isIndex",true);

        return "index";
    }
}
