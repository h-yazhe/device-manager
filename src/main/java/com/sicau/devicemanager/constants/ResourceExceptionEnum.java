package com.sicau.devicemanager.constants;

import lombok.Getter;

/**
 * 资源异常枚举
 * @author BeFondOfTaro
 * Created at 14:33 2018/4/23
 */
@Getter
public enum ResourceExceptionEnum {

    RESOURCE_NOT_FOUND(10,"资源不存在")
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常
     */
    private String exception;

    ResourceExceptionEnum(Integer code, String exception){
        this.code = code;
        this.exception = exception;
    }
}
