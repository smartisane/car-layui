package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service("userService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 登陆
     */
    @Override
    public Result login(User user) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            subject.login(token);
            User principal = (User) subject.getPrincipal();
            logger.info("userService中通过subject获取的principal="+principal);
            Session session = subject.getSession();
            session.setAttribute("user",principal);
            return Result.success("登陆成功");
        } catch (UnknownAccountException e) {
            logger.debug("用户名错误");
            return Result.error("用户名错误");
        } catch (IncorrectCredentialsException e) {
            logger.debug("密码错误");
            return Result.error("密码错误");
        }
    }

    /**
     * 注册
     */
    @Override
    public Result register(User user, HttpSession session) {

        user.setCreateTime(new Date());
        user.setAvatar("/img/default-avatar.jpg");
        user.setStatus(1);
        user.setSex("保密");
        //说明是修改
        if(user.getMod()==1){
            try{
                UpdateWrapper<User> wrapper = new UpdateWrapper<>();
                wrapper.eq("username",user.getUsername());
                userMapper.update(user,wrapper);
                //修改成功后将新的数据放到session
                User modUser = userMapper.selectOne(wrapper);
                session.setAttribute("user",modUser);
                return Result.success("修改成功");
            }catch (Exception e){
                e.printStackTrace();
                return Result.error("修改失败");
            }

        }
        logger.debug("即将注册user："+user.toString());
        try {
            userMapper.insert(user);
            return Result.success("注册成功");
        } catch (Exception e) {
            //出现异常说明已存在该username，违反唯一性约束
            logger.debug("注册失败，该用户名已被占用");
            return Result.error("注册失败，该用户名已被占用");
        }
    }
}
