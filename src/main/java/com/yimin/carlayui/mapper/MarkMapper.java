package com.yimin.carlayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimin.carlayui.common.MarkVo;
import com.yimin.carlayui.entity.Mark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MarkMapper extends BaseMapper<Mark> {
    @Select("select mark.id,mark.user_id,mark.create_time,mark.car_id,car.type,car.rent,car.thumbnail_url,car.address,car.car_describe from mark,car where mark.user_id=#{userId} and mark.car_id=car.id")
    List<MarkVo> selectMarkInfo(@Param("userId") Long userId);
}
