package com.sicau.devicemanager.service.impl;

import com.sicau.devicemanager.POJO.DO.UserAuth;
import com.sicau.devicemanager.config.exception.BusinessException;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.dao.UserAuthMapper;
import com.sicau.devicemanager.dao.UserMapper;
import com.sicau.devicemanager.service.LoginService;
import com.sicau.devicemanager.util.DateUtil;
import com.sicau.devicemanager.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author BeFondOfTaro
 * Created at 21:10 2018/5/16
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * token过期时间(天)
     */
    @Value("${token-expire-time}")
    private int tokenExpireTime;

    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(UserAuth userAuthRequest) {
    	//先校验验证码
		String validationToken = userAuthRequest.getValidationToken();
		String imageValidationCode = userAuthRequest.getImageValidationCode();
		// 判断验证码是否过期或错误
		if (!redisTemplate
				.hasKey(CommonConstants.RedisKey.WEB_IMAGE_VALIDATION_CODE_PREFIX + validationToken)) {
				throw new BusinessException("验证码已过期");
		}
		// 验证码是否正确
		if (!redisTemplate.opsForValue()
				.get(CommonConstants.RedisKey.WEB_IMAGE_VALIDATION_CODE_PREFIX + validationToken)
				.equals(imageValidationCode.toLowerCase())) {
			throw new BusinessException("请输入正确的验证码");
		}
		//密码校验
		String identifier = userAuthRequest.getIdentifier();
		//密码传输是用base64编码，需要先解码
		String credential = new String(Base64.getDecoder().decode(userAuthRequest.getCredential()));
		Integer identifyType = userAuthRequest.getIdentifyType();
        UserAuth userAuth = userAuthMapper.getUserAuthByLoginInfo(identifier, credential, identifyType);
        if (null == userAuth) {
            throw new BusinessException(BusinessExceptionEnum.USER_AUTH_EXCEPTION);
        }
        //根据userId，密码，token过期时间生成token
        String token = JWTUtil.sign(userAuth.getUserId(),
                credential,
                DateUtil.convertDay2Millisecond(tokenExpireTime));
        //存入redis
        redisTemplate.boundValueOps(CommonConstants.RedisKey.AUTH_TOKEN_PRIFIX + userAuth.getUserId()).
                set(token, tokenExpireTime, TimeUnit.DAYS);
        //返回给客户端
        Map<String, Object> res = new HashMap<>();
        res.put(HttpParamKey.TOKEN, token);
        res.put("userInfo", userMapper.getUserById(userAuth.getUserId()));
        return res;
    }

}
