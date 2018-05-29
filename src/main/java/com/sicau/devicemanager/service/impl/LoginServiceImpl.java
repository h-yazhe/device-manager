package com.sicau.devicemanager.service.impl;

import com.sicau.devicemanager.POJO.DO.UserAuth;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.UserAuthMapper;
import com.sicau.devicemanager.service.LoginService;
import com.sicau.devicemanager.util.DateUtil;
import com.sicau.devicemanager.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Override
    public String login(String identifier, String credential, Integer identifyType) {
        UserAuth userAuth = userAuthMapper.getUserAuthByLoginInfo(identifier, credential, identifyType);
        if (null == userAuth){
            throw new CommonException(ResultEnum.LOGIN_FAILED);
        }
        //根据userId，密码，token过期时间生成token
        return JWTUtil.sign(userAuth.getUserId(),
                userAuthMapper.getPasswordByUserId(userAuth.getUserId()),
                DateUtil.convertDay2Millisecond(tokenExpireTime));
    }

}
