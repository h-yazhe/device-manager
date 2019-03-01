package com.sicau.devicemanager.config.exception;

import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import lombok.Getter;

/**
 * 业务异常
 * @author BeFondOfTaro
 * Created at 13:56 2018/3/14
 */
@Getter
public class BusinessException extends RuntimeException{

	private Integer code;

	/**
	 * 通常情况都使用改构造方法
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
		code = ResultEnum.BUSINESS_EXCEPTION.getCode();
	}

	/**
	 * 最高级别的异常使用此构造方法（基本不使用）
	 * @param resultEnum
	 */
	public BusinessException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		code = resultEnum.getCode();
	}

	/**
	 * 前端需要通过code判断业务异常类型时使用此构造方法，并且message应是对应枚举异常同类的消息
	 * @param businessExceptionEnum
	 * @param message
	 */
	public BusinessException(BusinessExceptionEnum businessExceptionEnum, String message) {
		super(message);
		code = businessExceptionEnum.getCode();
	}

	/**
	 * 前端需要通过code判断业务异常类型时使用此构造方法
	 * @param businessExceptionEnum
	 */
	public BusinessException(BusinessExceptionEnum businessExceptionEnum) {
		super(businessExceptionEnum.getException());
		code = businessExceptionEnum.getCode();
	}
}
