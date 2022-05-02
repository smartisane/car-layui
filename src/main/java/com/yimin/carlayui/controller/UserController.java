package com.yimin.carlayui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.service.UserService;
import com.yimin.carlayui.util.ValidationUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     */
    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody User user) {
        System.out.println("user="+user);
        final List<String> list = ValidationUtil.validate(user);
        // 通过验证，交给service处理
        if (list == null) {
            return userService.login(user);
        }
        return Result.error("请完善信息");
    }

    /**
     * 注册
     */
    @PostMapping(value = "/register",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result register(@RequestBody User user,HttpSession session) {
        System.out.println("user="+user);
        // 添加Register组进行校验，防止空输入
        List<String> list = ValidationUtil.validate(user,User.Register.class, Default.class);
        // 通过验证，交给service处理
        if (list == null) {
            return userService.register(user,session);
        }
        return Result.error("请完善信息");
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
