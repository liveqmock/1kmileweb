package com.yeahwell.epu.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author yeahwell
 * 
 */
//@Component
//@Aspect
public class ServiceLogAspect {

	private final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

	/**
	 * 定义切入点
	 */
	@Pointcut("execution(public * com.yeahwell.epu.service..*.*(..))")
	public void logMethod() {
		logger.info("模拟基础的日志记录");
	}


	/**
	 * 
	 * @param pjp
	 * @return 返回值一定要为Object,否则会一直报空指针异常
	 * @throws Throwable
	 */
	@Around("logMethod()")
	public Object doLogAround(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = pjp.proceed();
		logger.info("service-----AOP");
		return obj;
	}


}
