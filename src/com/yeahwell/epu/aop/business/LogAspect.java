package com.yeahwell.epu.aop.business;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志记录功能(原本应该再新建一张日志记录表的) 此处为了方便,直接输出 为service层的所有方法执行日志记录功能
 * 
 * @author yeahwell
 * 
 */
@Component
@Aspect
public class LogAspect {

	private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	/**
	 * 定义切入点
	 */
	@Pointcut("execution(public * com.yeahwell.epu.*.business.*.*(..))")
	public void logMethod() {
		logger.info("模拟基础的日志记录");
	}

	/**
	 * 前置通知
	 * 
	 * @param pj
	 * @param obj
	 */
	@Before("logMethod()")
	public void doLogBefore(JoinPoint pj) {
		String methodName = pj.getSignature().getName();
		StringBuilder sb = new StringBuilder();
		sb.append("方法" + methodName + "的输入参数为-------");
		Object[] objs = pj.getArgs();
		for (Object obj : objs) {
			sb.append(obj);
			sb.append("\t");
		}
		logger.info(sb.toString());
	}

	/**
	 * 后置通知
	 * 
	 * @param pj
	 * @param result
	 */
	@AfterReturning(pointcut = "logMethod()", returning = "result")
	public void doAfterReturning(JoinPoint pj, Object result) {
		String methodName = pj.getSignature().getName();
		logger.info("方法{}的返回参数为-------{}", methodName, result);
	}

	/**
	 * 
	 * @param pjp
	 * @return 返回值一定要为Object,否则会一直报空指针异常
	 * @throws Throwable
	 */
	@Around("logMethod()")
	public Object doLogAround(ProceedingJoinPoint pjp) throws Throwable {
		String clazzString = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String fullPath = clazzString + "." + methodName;
		int flag = clazzString.indexOf("$");
		if (flag < 0) {
			logger.info("开始业务处理[{}];全路径[{}]", methodName, fullPath);
		}
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long end = System.currentTimeMillis();
		if (flag < 0) {
			logger.info(String.format("结束业务处理[%s];耗时:%s毫秒;全路径[%s]", methodName,
					(end - start), fullPath));
		}
		return obj;
	}

	/**
	 * @param jp
	 * @param ex
	 */
	@AfterThrowing(pointcut = "logMethod()", throwing = "ex")
	public void doLogThrowing(JoinPoint jp, Throwable ex) {
		String clazzString = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String fullPath = clazzString + "." + methodName;
		int flag = clazzString.indexOf("$");
		if (flag < 0) {
			logger.info("业务处理时发生了异常:[" + fullPath + "]");
			logger.error(ex.getMessage());
		}
	}

}
