package com.sicau.devicemanager.config.exception.handle;

import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.config.exception.ResourceException;
import com.sicau.devicemanager.constants.ResourceExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller的统一异常处理
 * @author BeFondOfTaro
 * Created at 23:00 2018/3/15
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e){
        //如果是我们自定义的异常
        if (e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            return ResultVOUtil.error(commonException.getCode(),commonException.getMessage());
        }
        //权限异常
        else if (e instanceof UnauthorizedException){
            UnauthorizedException unauthorizedException = (UnauthorizedException) e;
            return ResultVOUtil.error(ResultEnum.UNAUTHORIZED.getCode(),unauthorizedException.getMessage());
        }
        //资源异常
        else if (e instanceof ResourceException){
        	e.printStackTrace();
            ResourceException resourceException = (ResourceException) e;
            ResourceExceptionEnum resourceExceptionEnum = resourceException.getResourceExceptionEnum();
            return ResultVOUtil.error(resourceExceptionEnum.getCode(),
                    "资源：" + resourceException.getResource() + ";" + resourceExceptionEnum.getException());
        }
        //如果不是我们自定义的异常
        else {
            log.error("【系统异常】{}",e);
            return ResultVOUtil.error(
                    ResultEnum.UNKNOWN_ERROR.getCode(),
                    e.getMessage());
        }
    }
}
