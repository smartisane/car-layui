package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {
    Result login(User user);

    Result register(User user,HttpSession session);
}
