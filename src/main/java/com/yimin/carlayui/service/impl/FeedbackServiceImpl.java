package com.yimin.carlayui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimin.carlayui.mapper.FeedbackMapper;
import com.yimin.carlayui.entity.Feedback;
import com.yimin.carlayui.service.FeedbackService;
import org.springframework.stereotype.Service;

/**
 * 反馈表(Feedback)表服务实现类
 *
 * @author makejava
 * @since 2022-05-02 19:38:21
 */
@Service("feedbackService")
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

}

