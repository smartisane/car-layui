package com.yimin.carlayui.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.yimin.carlayui.common.FeedbackStatus;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.Feedback;
import com.yimin.carlayui.entity.News;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.service.FeedbackService;
import com.yimin.carlayui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 反馈表(Feedback)表控制层
 *
 * @author makejava
 * @since 2022-05-02 19:38:12
 */
@Controller
public class FeedbackController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;


    @GetMapping("/feedbackPage")
    public String feedbackPage(Model model) {
        model.addAttribute("isFeedbackPage", true);
        return "feedback";
    }


    /**
     * 提交反馈
     *
     * @return
     */
    @PostMapping(value = "/submitFeedback", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result submitFeedback(@RequestBody Feedback feedback, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //接口测试
        if (user == null) {
            return Result.error("请先登陆，再提交反馈");
        }


        // 反馈后1小时内不能再次反馈
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        wrapper.orderByDesc("create_time");
        List<Feedback> feedbackList = feedbackService.list(wrapper);
        logger.debug("feedbackList=" + feedbackList);
        if (feedbackList != null && feedbackList.size() > 0) {
            Feedback lastFeedback = feedbackList.get(0);
            Date lastFeedbackTime = lastFeedback.getCreateTime();
            long time1 = lastFeedbackTime.getTime();
            long time2 = System.currentTimeMillis();
            if (time2 / 3600000 - time1 / 3600000 < 1) {
                return Result.error("反馈过于频繁，请稍后再试");
            }
        }

        //补充创建时间信息
        feedback.setCreateTime(new Date());
        feedback.setUserId(user.getId());
        feedback.setUsername(user.getUsername());
        feedback.setStatus(FeedbackStatus.NOT_HANDLE);//待处理
        //插入信息
        try {
            feedbackService.save(feedback);
            return Result.success("反馈已成功提交，请等待处理");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("提交失败");
        }

    }


    @GetMapping("/feedbackMPage")
    public String feedbackMPage(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");

        //只有管理员才能访问
        if(user==null ){
            return "error/404";
        }else if("admin".equals(user.getRole())){
            QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            List<Feedback> feedbackList = feedbackService.list(wrapper);
            model.addAttribute("feedbackList",feedbackList);
        }else if("customer".equals(user.getRole())){
            QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",user.getId());
            wrapper.orderByDesc("create_time");
            List<Feedback> feedbackList = feedbackService.list(wrapper);
            model.addAttribute("feedbackList",feedbackList);
        }

        //高亮菜单
        model.addAttribute("isFeedbackMPage",true);
        return "feedback_manage";

    }


    @PostMapping("/submitReply")
    @ResponseBody
    public Result submitReply(@RequestBody Map<String,String> map,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user==null || !"admin".equals(user.getRole())){
            return Result.error("未登陆或没有权限");
        }
        Long id = Long.valueOf(map.get("reply-id"));
        String content = map.get("reply-content");
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setReply(content);
        //设置状态为已处理
        feedback.setStatus(FeedbackStatus.HAS_HANDLE);

        try {
            feedbackService.updateById(feedback);
            return Result.success("已回复");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }


}

