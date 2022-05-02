package com.yimin.carlayui.common;

import lombok.Data;

@Data
public class SearchVo {
    private String address;
    private String type;
    private Integer rent;
    private Integer minRent;
    private Integer maxRent;
}
