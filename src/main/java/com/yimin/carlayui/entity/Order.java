package com.yimin.carlayui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("car_order")
public class Order {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private Long customerId;
    private Long ownerId;
    private Long carId;
    private Integer status;
    private Integer rent;
    private Integer deposit;
    private Long days;
    private Integer total;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;

}
