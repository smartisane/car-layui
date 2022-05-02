package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yimin.carlayui.common.MarkVo;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.Mark;

import java.util.List;

public interface MarkService extends IService<Mark> {
    /**
     * 当前用户是否收藏了当前汽车
     * @param userId
     * @param carId
     * @return
     */
    public boolean isMarked(Long userId,Long carId);


    /**
     * 提交收藏
     * @param userId
     * @param carId
     * @return
     */
    Result submitMark(Long userId,Long carId);

    /**
     * 取消收藏
     * @param userId
     * @param carId
     * @return
     */
    Result cancelMark(Long userId,Long carId);



    /**
     * 获取当前登陆用户的收藏，包含相应的汽车相关信息
     * @param userId
     * @return
     */
    List<MarkVo> getMarkInfoList(Long userId);
}
