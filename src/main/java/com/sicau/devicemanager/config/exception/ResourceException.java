package com.sicau.devicemanager.config.exception;

import com.sicau.devicemanager.constants.ResourceExceptionEnum;
import lombok.Getter;

/**
 * 资源异常
 * @author BeFondOfTaro
 * Created at 14:23 2018/4/23
 */
@Getter
public class ResourceException extends RuntimeException {

    /**
     * 异常类型枚举
     */
    private ResourceExceptionEnum resourceExceptionEnum;

    /**
     * 资源
     */
    private String resource;

    public ResourceException(ResourceExceptionEnum resourceExceptionEnum, String resource) {
        this.resourceExceptionEnum = resourceExceptionEnum;
        this.resource = resource;
    }
}
