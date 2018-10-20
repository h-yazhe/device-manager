package com.sicau.devicemanager.util;

import com.sicau.devicemanager.constants.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(int code, Class<T> clazz) {
        for (T t : clazz.getEnumConstants()) {
            if (t.getCode() == code) {
                return t;
            }
        }
        return null;
    }
}
