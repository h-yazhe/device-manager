package com.sicau.devicemanager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    /**
     * 过期时间5分钟
     */
    public static final long DEFAULT_EXPIRE_TIME = 5*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userId, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param userId 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String userId, String secret) {
        return sign(userId, secret, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 生成签名
     * @param userId 用户名
     * @param secret 密码
     * @param expireTime 过期时间
     * @return
     */
    public static String sign(String userId, String secret, long expireTime){
        try {
            Date nowDate = new Date();
            Date date = new Date(System.currentTimeMillis()+expireTime);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带userid信息
            return JWT.create()
                    //签发时间
                    .withIssuedAt(nowDate)
                    //过期时间
                    .withExpiresAt(date)
                    //系统时间之前的token都是不可以被承认的
                    .withNotBefore(nowDate)
                    //附加的信息
                    .withClaim("userId", userId)
                    //使用密码签发
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
