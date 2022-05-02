package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimin.carlayui.common.MarkVo;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.Mark;
import com.yimin.carlayui.mapper.MarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("markService")
@Transactional
public class MarkServiceImpl extends ServiceImpl<MarkMapper, Mark> implements MarkService {

    @Autowired
    private MarkMapper markMapper;

    @Override
    public boolean isMarked(Long userId, Long carId) {
        QueryWrapper<Mark> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("car_id", carId);
        Mark mark = markMapper.selectOne(wrapper);
        if (mark == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Result submitMark(Long userId, Long carId) {
        QueryWrapper<Mark> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("car_id", carId);
        Mark mark = markMapper.selectOne(wrapper);
        //没有收藏过
        if (mark == null) {
            Mark mark1 = new Mark(null, new Date(), userId, carId);
            markMapper.insert(mark1);
            return Result.success("收藏成功");
        } else {
            markMapper.deleteById(mark.getId());
            return Result.success("已取消收藏");
        }
    }

    /**
     * 取消收藏
     * @param userId
     * @param carId
     * @return
     */
    @Override
    public Result cancelMark(Long userId, Long carId) {
        QueryWrapper<Mark> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("car_id", carId);
        try{
            markMapper.delete(wrapper);
            return Result.success("已取消收藏");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("取消收藏失败");
        }
    }


    @Override
    public List<MarkVo> getMarkInfoList(Long userId) {
        return markMapper.selectMarkInfo(userId);
    }
}
