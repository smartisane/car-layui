package com.yimin.carlayui.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success(String msg){
        return new Result(1,msg);
    }

    public static Result success(String msg,Object data){
        return new Result(1,msg,data);
    }

    public static Result error(String msg){
        return new Result(0,msg);
    }

}
