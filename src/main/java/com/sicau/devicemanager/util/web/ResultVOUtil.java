package com.sicau.devicemanager.util.web;


import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;

/**
 * http响应的工具类
 * @author BeFondOfTaro
 * Created in 12:24 2018/1/18
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return resultVO;
    }

	/**
	 * 返回系统异常
	 * @return
	 */
	public static ResultVO retSysError(){
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
		resultVO.setMsg(ResultEnum.UNKNOWN_ERROR.getMessage());
		return resultVO;
	}

	/**
	 * 返回自定义错误信息的异常
     * @param msg 自定义错误信息
	 */
    public static ResultVO retSysError(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        resultVO.setMsg(msg);
        return resultVO;
    }

	/**
	 * 返回业务异常
	 * @param businessExceptionEnum
	 * @return
	 */
	public static ResultVO retBusinessError(BusinessExceptionEnum businessExceptionEnum) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(businessExceptionEnum.getCode());
		resultVO.setMsg(businessExceptionEnum.getException());
		return resultVO;
	}
}
