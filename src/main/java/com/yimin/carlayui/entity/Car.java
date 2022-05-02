package com.yimin.carlayui.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 汽车实体类
 */
@TableName("car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private Long userId;
    private String carId;
    @NotEmpty
    private String type;
    @NotEmpty
    private String carDescribe;
    @NotEmpty
    private Integer rent;
    @NotEmpty
    private Integer deposit;
    private Integer status;
    private String thumbnailUrl;
    private String slideUrl;
    @NotEmpty
    private String address;
    @NotEmpty
    private String contactName;
    @NotEmpty
    private String contactPhone;
    //上传图片时用到，其他时候无意义,因忽略
    @TableField(exist = false)
    private String key;

    @TableLogic
    private Integer isDeleted;

    //轮播图
    @TableField(exist = false)
    private List<String> slideList;
}
