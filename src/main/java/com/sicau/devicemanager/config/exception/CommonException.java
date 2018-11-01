package com.sicau.devicemanager.config.exception;

import com.sicau.devicemanager.constants.ResultEnum;
import lombok.Getter;

/**
 * 通用异常
 * @author BeFondOfTaro
 * Created at 13:56 2018/3/14
 */
@Getter
public class CommonException extends RuntimeException {

    private Integer code;

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
