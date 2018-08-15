package com.sicau.devicemanager.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Yazhe
 * Created at 17:08 2018/8/14
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	//定义切点
	@Pointcut("execution(public * com.sicau.devicemanager.controller.*.*(..))")
	public void pointCut(){

	}

	@Before("pointCut()")
	public void doBefore(JoinPoint joinPoint){
		logger.info("请求参数：{}", Arrays.toString(joinPoint.getArgs()));
	}
}
