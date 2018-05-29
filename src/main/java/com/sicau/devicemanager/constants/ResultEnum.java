package com.sicau.devicemanager.constants;

import lombok.Getter;

/**
 * http响应数据状态code
 * @author BeFondOfTaro
 * Created in 12:43 2018/1/18
 */
@Getter
public enum ResultEnum {

    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0,"成功"),
    PARAM_ERROR(1, "参数不正确"),
    LOGIN_FAILED(2, "登录失败"),
    LOGIN_EXCEPTION(3, "登录异常"),
    USER_NOT_FOUND(4, "用户不存在"),
    PASSWORD_INCORRECT(5, "密码不正确"),
    INVALID_TOKEN(6, "无效的token"),
    TOKEN_EXPIRED(7, "过期的token"),
    UNAUTHORIZED(8, "没有权限"),
    DELETE_FAILED(9, "删除失败"),

    RESOURCE_NOT_FOUND(10, "资源不存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
