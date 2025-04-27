package com.example.BasicProjectTemplate.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor for authentication checks.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	/**
	 * Check authentication before controller method executes. Before Controller
	 * logic.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// Allow login page without checking
		if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/doLogin")) {
			return true;
		}

		// Check session attribute
		if (request.getSession().getAttribute("user") != null) {
			return true;
		}

		// If not authenticated, redirect to login
		response.sendRedirect("/login");
		return false;

	}
}