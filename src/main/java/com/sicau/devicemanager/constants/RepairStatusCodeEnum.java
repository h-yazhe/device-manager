package com.sicau.devicemanager.constants;

/**
 * @Author 黄师兄
 * 设备维修状态
 */
public enum RepairStatusCodeEnum {

    TO_BE_REPAIRED(0,"待维修"),
    BEING_REPAIRED(1, "维修中"),
    REPAIRED(2,"已维修"),
    CANNOT_REPAIRED(3, "无法维修"),
    ;
    /**
     * 状态码
     */
    Integer code;

    /**
     * 状态描述
     */
    String description;

    RepairStatusCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
