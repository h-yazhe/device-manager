package com.sicau.devicemanager.service;

/**
 * 登录相关服务
 * @author BeFondOfTaro
 * Created at 21:04 2018/5/16
 */
public interface LoginService {

    /**
     * 登录
     * @param identifier
     * @param credential
     * @param identifyType
     * @return 用户通行token
     */
    String login(String identifier,String credential,Integer identifyType);

}
