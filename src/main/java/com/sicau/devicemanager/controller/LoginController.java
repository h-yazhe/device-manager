package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.service.LoginService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVO login(@RequestParam String identifier,
                          @RequestParam String credential,
                          @RequestParam Integer identifyType){
        return ResultVOUtil.success(loginService.login(identifier, credential, identifyType));
    }
}
