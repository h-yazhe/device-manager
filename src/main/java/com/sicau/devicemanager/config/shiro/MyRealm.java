package com.sicau.devicemanager.config.shiro;

import com.sicau.devicemanager.POJO.DO.Permission;
import com.sicau.devicemanager.POJO.DO.Role;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.config.RedisConfig;
import com.sicau.devicemanager.config.shiro.token.SimpleToken;
import com.sicau.devicemanager.service.UserService;
import com.sicau.devicemanager.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
	private RedisTemplate<String,String> redisTemplate;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SimpleToken;
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
        // 获得userId，用于和数据库进行对比
        String userId = (String) auth.getPrincipal();
        if (userId == null) {
            throw new AuthenticationException("token无效");
        }
        //根据userId查询redis中保存的token，对比是否相同
		log.error(redisTemplate.boundValueOps(RedisConfig.DATABASE + ":" + userId + ":token").get());
        if (!token.equals(redisTemplate.boundValueOps(RedisConfig.DATABASE + ":" + userId + ":token").get())) {
            throw new AuthenticationException("token验证失败");
        }
        return new SimpleAuthenticationInfo(userId, token, getName());
    }
}
