package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.UserAuth;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.exception.BusinessException;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.Login;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.service.LoginService;
import com.sicau.devicemanager.util.validation.ValidationGenerator;
import com.sicau.devicemanager.util.validation.ValidationGeneratorImpl;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author BeFondOfTaro
 * Created at 23:40 2018/5/16
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
	private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/login")
    public ResultVO login(@Validated({Login.class}) @RequestBody UserAuth userAuth) {
        return ResultVOUtil.success(
                loginService.login(userAuth));
    }

	/**
	 * @param :
	 * @description : 生成临时token
	 * @author : hyz
	 * @date : 2017年10月17日下午1:45:07
	 */
	@ApiOperation(value = "用户获取临时token")
	@GetMapping("/interimToken")
	public Map<String, Object> getToken() {
		String token = UUID.randomUUID().toString();
		Map<String, Object> map = new HashMap<>(16);
		map.put("token", token);
		return map;
	}

	/**
	 * @param :
	 * @description : 获取图片验证码
	 * @author : fanmeng
	 * @date : 2017年10月13日下午3:49:59
	 */
	@ApiOperation(value = "用户获取图片验证码")
	@GetMapping("/imageValidation/{token}")
	public Map<String, Object> getValidateCode(@PathVariable("token") String token,
											   HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");

		ValidationGenerator vGeneratorImpl = new ValidationGeneratorImpl();
		String validationCode = vGeneratorImpl.getValidationCode();
		if (validationCode == null) {
			throw new BusinessException(BusinessExceptionEnum.USER_AUTH_EXCEPTION, "获取验证码失败");
		}

		// 将验证码存redis,并设置图片验证码失效时间
		String key = CommonConstants.RedisKey.WEB_IMAGE_VALIDATION_CODE_PREFIX
				+ token;
		redisTemplate.opsForValue().set(key, validationCode.toLowerCase(),
				CommonConstants.WEB_REDIS_KEY_EXPIRE_TIMES.WEB_LOGIN_USER_ID_TOKEN_EXPIRE_MINUTES,
				TimeUnit.MINUTES);

		// 取得验证码图片并输出
		BufferedImage bufferedImage = vGeneratorImpl.getBufferedImage();
		if (bufferedImage == null) {
			throw new BusinessException(BusinessExceptionEnum.USER_AUTH_EXCEPTION,"获取验证码失败");
		}
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			ImageIO.write(bufferedImage, "png", outputStream);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			vGeneratorImpl.destroy();
		}
		return null;
	}
}
