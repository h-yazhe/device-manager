package com.sicau.devicemanager.config.shiro.filter;

import com.sicau.devicemanager.config.shiro.token.JWTToken;
import com.sicau.devicemanager.config.shiro.token.SimpleToken;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.util.DateUtil;
import com.sicau.devicemanager.util.web.CookieUtil;
import com.sicau.devicemanager.util.web.ResponseUtil;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无状态身份认证过滤器
 * @author beFondOfTaro
 */
@Slf4j
public class AuthFilter extends AccessControlFilter {

    @Value("${token-expire-time}")
    private int tokenExpireTime;

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，会往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            String authorization = request.getHeader(HttpParamKey.TOKEN);
            //检测是否请求中包含token
            if (authorization == null){
                throw new Exception("token为空!");
            }
            //登录
            SimpleToken token = new SimpleToken(authorization);
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request, response).login(token);
            //登录成功后设置cookie，方便下次请求
            CookieUtil.addCookie(response,HttpParamKey.TOKEN, authorization,
                    DateUtil.convertDay2Second(tokenExpireTime));
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            onLoginFail(response);
            return false;
        }
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        return false;
    }

    /**
     * 登录失败时默认返回401 状态码
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseUtil.toJson(
                httpServletResponse,
                ResultVOUtil.error(ResultEnum.LOGIN_EXCEPTION)
                );
    }

}
