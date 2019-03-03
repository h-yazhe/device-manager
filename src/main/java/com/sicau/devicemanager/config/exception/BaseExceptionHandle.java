package com.sicau.devicemanager.config.exception;

import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.ResourceExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller的统一异常处理
 * @author BeFondOfTaro
 * Created at 23:00 2018/3/15
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandle {

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ResultVO handleBusinessException(BusinessException e) {
		log.error(e.getMessage(),e);
		return ResultVOUtil.error(e.getCode(),e.getMessage());
	}

	@ExceptionHandler(ResourceException.class)
	@ResponseBody
	public ResultVO handleResourceException(ResourceException e) {
		log.error(e.getMessage(),e);
		ResourceExceptionEnum resourceExceptionEnum = e.getResourceExceptionEnum();
		return ResultVOUtil.error(ResultEnum.RESOURCE_EXCEPTION.getCode(),
				"资源：" + e.getResource() + ";" + resourceExceptionEnum.getException());
	}

	private String parseBindingResult(BindingResult bindingResult){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("参数校验失败！");
		bindingResult.getFieldErrors().forEach(fieldError -> {
			stringBuilder.append("[字段名：");
			stringBuilder.append(fieldError.getField());
			stringBuilder.append(",错误信息:");
			stringBuilder.append(fieldError.getDefaultMessage());
			stringBuilder.append("]");
		});
		return stringBuilder.toString();
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), parseBindingResult(e.getBindingResult()));
	}

	@ExceptionHandler({BindException.class})
	@ResponseBody
	public ResultVO handleBindException(BindException e) {
		log.error(e.getMessage(), e);
		return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), parseBindingResult(e.getBindingResult()));
	}

	@ExceptionHandler(VerificationException.class)
	@ResponseBody
	public ResultVO handleVerificationException(VerificationException e) {
		log.error(e.getMessage(),e);
		return ResultVOUtil.error(
				ResultEnum.PARAM_ERROR.getCode(),
				"参数校验失败！" + e.getMessage());
	}

	@ExceptionHandler(SystemException.class)
	@ResponseBody
	public ResultVO handleException(SystemException e) {
		log.error(e.getMessage(),e);
		return ResultVOUtil.retSysError(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultVO handleException(Exception e) {
		log.error(e.getMessage(),e);
		return ResultVOUtil.retSysError(e.getMessage());
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResultVO handleThrowable(Throwable e) {
		log.error(e.getMessage(),e);
		return ResultVOUtil.retSysError();
	}
}
