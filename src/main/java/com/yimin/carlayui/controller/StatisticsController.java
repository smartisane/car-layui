package com.yimin.carlayui.controller;

import com.yimin.carlayui.common.Statistics;
import com.yimin.carlayui.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/statisticsPage")
    public String statisticsPage(Model model){

        List<Statistics> statisticsList = orderService.getTotalNum();
        model.addAttribute("statisticsList",statisticsList);
        model.addAttribute("isStatisticsPage",true);
        return "statistics";
    }
}
