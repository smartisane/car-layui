package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimin.carlayui.entity.Car;
import com.yimin.carlayui.mapper.CarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("carService")
@Transactional
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
