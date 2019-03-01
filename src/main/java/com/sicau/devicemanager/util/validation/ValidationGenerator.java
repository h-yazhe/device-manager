package com.sicau.devicemanager.util.validation;

import java.awt.image.BufferedImage;

/**
 *
 * @class : ValidationGenerator
 * @description : 图片验证码生成接口
 * @author ：fanmeng
 * @date ：2017年10月13日下午2:42:43
 */
public interface ValidationGenerator {

	public void init();
	public String getValidationCode() ;
	public void destroy() ;
	public BufferedImage getBufferedImage();
}
