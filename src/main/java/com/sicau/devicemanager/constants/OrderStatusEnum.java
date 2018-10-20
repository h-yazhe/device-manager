package com.sicau.devicemanager.constants;

import lombok.Data;

/**
 * 订单状态的枚举
 *
 * @author Xiao W
 */
public enum OrderStatusEnum implements CodeEnum{
    //订单状态，0:待维修，1:维修中，2:已维修,3:维修失败
    TO_BE_REPAIRED(0,"待维修"),
    IN_MAINTENANCE(1,"维修中"),
    FINISHED(2,"已维修"),
    FAILED(3,"维修失败");

    private int code;
    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
