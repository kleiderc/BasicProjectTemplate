/**
 * 
 */
package com.example.BasicProjectTemplate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//AOP Aspect for logging method calls
@Slf4j
@Aspect
@Component
public class LoggingAspect {

	// Pointcut for all methods in controller and service packages
	@Pointcut("execution(* com.example.BasicProjectTemplate.controller..*(..)) || execution(* com.example.BasicProjectTemplate.service..*(..))")
	public void applicationPackagePointcut() {
	}

	// Before advice
	@Before("applicationPackagePointcut()")
	public void logBefore(JoinPoint joinPoint) {
		log.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
	}

	// After returning advice
	@AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
	}

	// After throwing advice
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in method: {} with cause: {}", joinPoint.getSignature(),
				e.getCause() != null ? e.getCause() : "NULL");
	}
}
