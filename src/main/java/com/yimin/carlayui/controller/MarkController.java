package com.yimin.carlayui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimin.carlayui.common.MarkVo;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.Mark;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.service.CarService;
import com.yimin.carlayui.service.MarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class MarkController {

    @Autowired
    private CarService carService;

    @Autowired
    private MarkService markService;

    /**
     * 转到收藏页，并显示数据
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/carMarkPage")
    public String carMarkPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "error/404";
        }
        Long userId = user.getId();
        List<MarkVo> markInfoList = markService.getMarkInfoList(userId);
        model.addAttribute("markInfoList",markInfoList);
        model.addAttribute("isMarkPage",true);
        return "mark";
    }

    @PostMapping(value = "/cancelMark", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result cancelMark(@RequestBody String carId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Result result = markService.cancelMark(user.getId(), Long.parseLong(carId));
            return result;
        } else {
            return Result.error("请先登录");
        }

    }

}
