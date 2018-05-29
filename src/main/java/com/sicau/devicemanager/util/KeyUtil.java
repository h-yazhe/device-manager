package com.sicau.devicemanager.util;

import java.util.Random;

/**
 * @author BeFondOfTaro
 * Created at 11:59 2018/1/18
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
