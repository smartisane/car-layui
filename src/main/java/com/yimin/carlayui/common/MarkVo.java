package com.yimin.carlayui.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MarkVo {
    private long id;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //和car相关
    private Long carId;
    private String type;
    private Integer rent;
    private String thumbnailUrl;
    private String address;
    private String carDescribe;
}
