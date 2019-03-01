package com.sicau.devicemanager.constants;

/**
 * 通用常量类
 * @author BeFondOfTaro
 * Created in 16:36 2018/1/18
 */
public interface CommonConstants {

    /**
     * api版本号
     */
    String API_PREFIX = "/api_v1";

    /**
     * redis缓存时的键
     */
    interface RedisKey {
        /**
         * 业务前缀
         */
        String BUSINESS_PREFIX = "dev_man:";

        /**
         * 用户登录认证token前缀
         */
        String AUTH_TOKEN_PRIFIX = BUSINESS_PREFIX + "token:";

		/**
		 * 图片验证码前缀
		 */
		String WEB_IMAGE_VALIDATION_CODE_PREFIX = "webImageValidationCode_";

    }

	/**
	 * WEB redis key失效时间
	 */
	interface WEB_REDIS_KEY_EXPIRE_TIMES {

		/**
		 * 登录用户ID的token KEY失效分钟数
		 */
		int WEB_LOGIN_USER_ID_TOKEN_EXPIRE_MINUTES = 30;

		// 登录用户名盐值 KEY失效分钟数
		int WEB_LOGIN_NAME_SALT_EXPIRE_MINUTES = 30;

		// 注册用户验证码KEY失效分钟数
		int WEB_CELLPHONE_MESSAGE_CODE_EXPIRE_MINUTES = 5;
	}
}
