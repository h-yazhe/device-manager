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
	PASSWORD_INCORRECT(5, "用户名或密码不正确"),
	INVALID_TOKEN(6, "登录已过期，请重新登录"),
	UNAUTHENTICATED(7, "请先登录"),
	UNAUTHORIZED(8, "没有权限"),

	RESOURCE_NOT_FOUND(10, "资源不存在"),
	RESOURCE_EXCEPTION(11, "资源异常"),
	BUSINESS_EXCEPTION(12, "业务异常"),
    UPLOAD_FAILED(13,"上传失败")
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
