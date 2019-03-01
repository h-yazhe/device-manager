package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.UserAuth;

import java.util.Map;

/**
 * 登录相关服务
 * @author BeFondOfTaro
 * Created at 21:04 2018/5/16
 */
public interface LoginService {

    /**
     * 登录
     * @return 用户通行token
     */
    Map<String, Object> login(UserAuth userAuthRequest);

}
