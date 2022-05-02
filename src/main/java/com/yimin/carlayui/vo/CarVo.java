package com.yimin.carlayui.vo;

import com.yimin.carlayui.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarVo extends Car {
    private String key;
}
