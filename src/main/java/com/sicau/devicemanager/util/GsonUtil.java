package com.sicau.devicemanager.util;

import com.google.gson.Gson;

/**
 * 获取gson对象的工具类
 * @author Yazhe
 * Created at 9:42 2018/8/22
 */
public class GsonUtil {

	private static Gson gson = new Gson();

	public static Gson getGson(){
		return gson;
	}
}
