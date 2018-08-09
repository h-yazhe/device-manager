package com.sicau.devicemanager.constants;

/**
 * @author Yazhe
 * Created at 12:03 2018/8/9
 */
public enum BusinessExceptionEnum {

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
