package com.example.BasicProjectTemplate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor for logging each HTTP request and response.
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * Called before the controller method is executed.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("[REQUEST] {} {}", request.getMethod(), request.getRequestURI());
        return true; // Proceed to the next interceptor or controller
    }

    /**
     * Called after the complete request is finished (after view rendering).
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("[RESPONSE] {} {} - Status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }
}