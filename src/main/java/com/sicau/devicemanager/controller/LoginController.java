package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.UserAuth;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.Login;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.service.LoginService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author BeFondOfTaro
 * Created at 23:40 2018/5/16
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResultVO login(@Validated({Login.class}) @RequestBody UserAuth userAuth){
        return ResultVOUtil.success(loginService.login(userAuth.getIdentifier(), userAuth.getCredential(), userAuth.getIdentifyType()));
    }
}
