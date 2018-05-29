package com.sicau.devicemanager.config.shiro;

import com.sicau.devicemanager.POJO.DO.Permission;
import com.sicau.devicemanager.POJO.DO.Role;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.service.UserService;
import com.sicau.devicemanager.util.JWTUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = principals.toString();
        UserDTO user = userService.getUserById(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoleList()){
            simpleAuthorizationInfo.addRole(role.getName());
        }
        for (Permission permission : user.getPermissionList()){
            simpleAuthorizationInfo.addStringPermission(permission.getPermissionCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得userId，用于和数据库进行对比
        String userId = JWTUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }
        //根据userId查询密码，生成token，和客户端token比对
        if (! JWTUtil.verify(token, userId, userService.getPasswordByUserId(userId))) {
            throw new AuthenticationException("token验证失败");
        }
        return new SimpleAuthenticationInfo(JWTUtil.getUserId(token), token, getName());
    }
}
