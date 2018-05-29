package com.sicau.devicemanager.util;

/**
 * 日期工具
 * @author BeFondOfTaro
 * Created at 14:44 2018/5/18
 */
public class DateUtil {

    /**
     * 天转毫秒
     * @param day 天数
     * @return
     */
    public static long convertDay2Millisecond(int day){
        return (long)day*86400000;
    }

    /**
     * 天转秒
     * @param day 天数
     * @return
     */
    public static int convertDay2Second(int day){
        return day*86400;
    }
}
