package com.yimin.carlayui.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 使用jdk自带的api实现两个日期相隔天数计算
 * 在选择时间范围，预定时用到
 */
public class DateUtil {

    /**
     * 两个日期相隔天数
     * @param startDataStr 开始日期
     * @param endDateStr 结束日期
     * @return 相隔天数
     * @throws DateTimeParseException 传入日期字符串有误时抛出异常
     */
    public static long daysBetween(String startDateStr,String endDateStr) throws DateTimeParseException {

        LocalDate parse = LocalDate.parse(startDateStr);
        LocalDate parse1 = LocalDate.parse(endDateStr);
        return ChronoUnit.DAYS.between(parse, parse1);

    }

    public static long daysBetween(Date startDate, Date endDate) throws DateTimeParseException {
        String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        String endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
        LocalDate parse = LocalDate.parse(startDateStr);
        LocalDate parse1 = LocalDate.parse(endDateStr);
        return ChronoUnit.DAYS.between(parse, parse1);

    }

    /**
     * test
     */
    public static void main(String[] args) {
        String dateStr1 = "2021-01-01";
        String dateStr2 = "2021-01-10";
        System.out.println(daysBetween(dateStr1, dateStr2));
        LocalDate localDate = LocalDate.now();//获取当前日期，格式为 2022-04-20
        System.out.println(localDate);

    }
}
