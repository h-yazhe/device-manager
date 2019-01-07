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
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    LOGIN_FAILED(2, "登录失败"),
    LOGIN_EXCEPTION(3, "登录异常"),
    USER_NOT_FOUND(4, "用户不存在"),
    PASSWORD_INCORRECT(5, "密码不正确"),
    INVALID_TOKEN(6, "无效的token"),
    TOKEN_EXPIRED(7, "过期的token"),
    UNAUTHORIZED(8, "没有权限"),
    DELETE_FAILED(9, "删除失败"),

    RESOURCE_NOT_FOUND(10, "资源不存在"),
    DATE_INCORRECT(11, "日期不正确"),
    ORDER_ID_NOT_PRESENT(12, "订单id不存在"),
    DEVICE_ID_NOT_PRESENT(13, "设备id不存在"),
    DEVICE_MODEL_NAME_REPEAT(14, "设备型号重名"),
	USERNAME_DUPLICATED(15,"已存在该用户名"),

    LOCATION_UNAUTHORIZED(100, "无该地点的权限"),
    ORDER_CANNOT_MODIFY(101, "修改失败，订单正在处理或已经处理"),
    ORDER_CANNOT_FINISHED(102, "完结订单失败，订单已完结，或还未接单"),
    ORDERS_DEVICE_STATUS_CANNOT_CHANGE(103, "不能更改设备状态，订单还没处理完成或设备状态已经更改"),
    DEVICE_ID_CANNOT_BE_NULL(104, "设备id不能为空"),
    ORDER_PARAMS_NOT_SATIFIED(105, "传入订单参数不满足");

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
