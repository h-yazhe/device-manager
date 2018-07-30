package com.sicau.devicemanager.config.shiro.token;

import com.sicau.devicemanager.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 简易token
 * @author Yazhe
 * Created at 16:19 2018/7/30
 */
public class SimpleToken implements AuthenticationToken {

	/**
	 * token令牌
	 */
	private String token;

	/**
	 * 获得userId
	 * @return
	 */
	@Override
	public Object getPrincipal() {
		return JWTUtil.getUserId(token);
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	public SimpleToken(String token) {
		this.token = token;
	}
}
