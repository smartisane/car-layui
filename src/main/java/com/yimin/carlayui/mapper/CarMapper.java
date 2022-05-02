package com.yimin.carlayui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yimin.carlayui.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CarMapper extends BaseMapper<Car> {
}
