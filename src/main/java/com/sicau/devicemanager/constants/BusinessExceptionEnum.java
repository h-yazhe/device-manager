package com.sicau.devicemanager.constants;

import lombok.Getter;

/**
 * @author Yazhe
 * Created at 12:03 2018/8/9
 */
@Getter
public enum BusinessExceptionEnum {
	DELETE_FAILED(9, "删除失败"),

	RESOURCE_NOT_FOUND(10, "资源不存在"),
	DATE_INCORRECT(11, "日期不正确"),
	ORDER_ID_NOT_PRESENT(12, "订单id不存在"),
	DEVICE_ID_NOT_PRESENT(13, "设备id不存在"),
	DEVICE_MODEL_NAME_REPEAT(14, "设备型号重名"),
	USERNAME_DUPLICATED(15,"已存在该用户名"),
	USER_AUTH_EXCEPTION(16,"用户登录失败"),

	LOCATION_UNAUTHORIZED(100, "无该地点的权限"),
	ORDER_CANNOT_MODIFY(101, "修改失败，订单正在处理或已经处理"),
	ORDER_CANNOT_FINISHED(102, "完结订单失败，订单已完结，或还未接单"),
	ORDERS_DEVICE_STATUS_CANNOT_CHANGE(103, "不能更改设备状态，订单还没处理完成或设备状态已经更改"),
	DEVICE_ID_CANNOT_BE_NULL(104, "设备id不能为空"),
	ORDER_PARAMS_NOT_SATIFIED(105, "传入订单参数不满足");
	;

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 异常
	 */
	private String exception;

	BusinessExceptionEnum(Integer code, String exception) {
		this.code = code;
		this.exception = exception;
	}
}
