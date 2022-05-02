package com.yimin.carlayui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import java.io.Serializable;

/**
 * 反馈表(Feedback)表实体类
 *
 * @author makejava
 * @since 2022-05-02 19:38:21
 */
@TableName("feedback")
@Data
public class Feedback {

    @TableId(type = IdType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String title;

    private String content;
    //用户id
    private Long userId;
    private String username;

    private Integer status;
    //回复
    private String reply;

}

