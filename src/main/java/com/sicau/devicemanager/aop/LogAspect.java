package com.sicau.devicemanager.aop;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.util.JWTUtil;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Modifier;
import java.util.*;

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
    public void pointCut() {

    }

    @Around("pointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		HttpServletResponse response = sra.getResponse();

		// 获取用户id
		String vAPSign = request.getHeader(HttpParamKey.TOKEN);
		if (!StringUtils.isEmpty(vAPSign)) {
			MDC.put("userId", JWTUtil.getUserId(vAPSign));
		}

		String url = request.getRequestURL().toString();
		//去除ip端口前缀
		String tempUrl =  url.substring(url.indexOf("//") + 2);
		url = tempUrl.substring(tempUrl.indexOf("/"));
		String method = request.getMethod();
		Object[] args = pjp.getArgs();

		MDC.put("url", request.getRequestURI());
		MDC.put("remoteHost", request.getRemoteHost());

		String classType = pjp.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		String clazzName = clazz.getName();
		// 获取方法名称`
		String methodName = pjp.getSignature().getName();
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Class[] argsType = methodSignature.getParameterTypes();
		// 获取参数名称和值
		Map<String, Object> nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName, args, argsType);

		// 过滤request,response对象
		Map<String, Object> filterNameAndArgs = new HashMap<>();
		if (nameAndArgs != null && nameAndArgs.size() > 0) {
			for (Map.Entry<String, Object> entry : nameAndArgs.entrySet()) {
				if (!(entry.getValue() instanceof MultipartFile || entry.getValue() instanceof ServletRequest
						|| entry.getValue() instanceof ServletResponse)) {
					filterNameAndArgs.put(entry.getKey(), entry.getValue());
				}
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> requestMap = new LinkedHashMap<>();
		String contentType = request.getContentType();
		requestMap.put("url", url);
		requestMap.put("method", method);
		requestMap.put("contentType", contentType);

		// 从Map中获取

		requestMap.put("methodInParams", filterNameAndArgs);
		logger.info("controller层请求参数:" + JSON.toJSONString(requestMap));

		// result的值就是被拦截方法的返回值
		Object result = pjp.proceed();

		int status = response.getStatus();
		long endTime = System.currentTimeMillis();
		Map<String, Object> responseMap = new LinkedHashMap<>();
		responseMap.put("status", status);
		responseMap.put("methodOutParams", result);
		responseMap.put("intTotalTime", endTime - startTime);
		MDC.put("totalTime", String.valueOf(endTime - startTime));
		objectMapper.writeValueAsString(responseMap);
		logger.info("controller层返回参数:" + JSON.toJSONString(responseMap));
		return result;
	}

	private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args,
											  Class[] argsType) throws Exception {
		Map<String, Object> map = new HashMap<>();
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(cls);
		pool.insertClassPath(classPath);

		CtClass cc = pool.get(clazzName);
		List<CtClass> params = new ArrayList<>();
		for (Class argType : argsType) {
			params.add(pool.get(argType.getName()));
		}
		CtMethod cm = cc.getDeclaredMethod(methodName, params.toArray(new CtClass[argsType.length]));
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			return null;
		}
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			// paramNames即参数名
			map.put(attr.variableName(i + pos), args[i]);
		}
		return map;
	}

}
