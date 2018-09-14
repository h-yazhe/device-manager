package com.sicau.devicemanager.util.web;

import org.apache.shiro.SecurityUtils;

/**
 * @author Yazhe
 * Created at 0:09 2018/9/15
 */
public class RequestUtil {

	/**
	 * 获取当前请求的用户id
	 * @return
	 */
	public static String getCurrentUserId(){
		return (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
	}
}
