/**
 * 
 */
package com.example.BasicProjectTemplate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//AOP Aspect for logging method calls
@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.example.BasicProjectTemplate.service..*(..))")
	public void beforeServiceMethods(JoinPoint joinPoint) {
		logger.debug("Entering method: {}", joinPoint.getSignature());
	}

	@Around("execution(* com.example.BasicProjectTemplate.service..*(..))")
	public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("[AOP] Entering method: {}", joinPoint.getSignature());
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long duration = System.currentTimeMillis() - start;
		logger.debug("[AOP] Exiting method: {} (Execution time: {} ms)", joinPoint.getSignature(), duration);
		return result;
	}

}
