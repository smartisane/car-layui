package com.yimin.carlayui.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotEmpty(groups = Register.class)//只有在注册时才不为null
    private String name;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty(groups = Register.class)
    private String phone;
    @NotEmpty(groups = Register.class)
    @Email
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private String idCard;
    private String avatar;
    private Integer status;
    private String role;
    private String sex;

    //当前是否是更新操作
    @TableField(exist = false)
    private int mod;

    @TableLogic
    private Integer isDeleted;

    // validation分组验证
    public interface Register{}
}
