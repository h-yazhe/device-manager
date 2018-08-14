package com.sicau.devicemanager.util;

import java.util.Calendar;
import java.util.Date;

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

	/**
	 * 获取一天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getStartTimeToday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}

	/**
	 * 获取一天的结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndTimeToday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,999);
		return calendar.getTime();
	}

}
