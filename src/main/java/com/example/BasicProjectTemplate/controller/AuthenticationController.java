package com.example.BasicProjectTemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BasicProjectTemplate.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Controller to handle all User-related routes
@Controller
public class AuthenticationController {

	private static final String LOGIN_VIEW = "authdto/login";
	private static final String REDIRECT_USER = "redirect:/user";
	private static final String REDIRECT_LOGIN = "redirect:/login";

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * Show login form - http://localhost:8080/login
	 * 
	 * @return - Redirects the browser to the final LOGIN template.
	 */
	@GetMapping("/login")
	public String loginForm() {
		return AuthenticationController.LOGIN_VIEW;
	}

	/**
	 * Process login
	 * 
	 * @param userId   - UserId from the LOGIN template.
	 * @param password - Password from the LOGIN template.
	 * @param request  - HTTP request
	 * @param response - HTTP response
	 * @param model    - An interface used to pass data from the controller to the
	 *                 view (like Thymeleaf HTML pages).
	 * @return - Redirects the browser to the final LOGIN template.
	 */
	@PostMapping("/doLogin")
	public String doLogin(@RequestParam String userId, @RequestParam String password, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (authenticationService.authenticate(userId, password)) {
			request.getSession().setAttribute("user", userId); // Store login info in session
			response.setStatus(HttpServletResponse.SC_OK);
			return AuthenticationController.REDIRECT_USER;
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set HTTP 401 Unauthorized
			model.addAttribute("error", "Invalid userId or password!");
			return AuthenticationController.LOGIN_VIEW;
		}
	}

	/**
	 * Logout - http://localhost:8080/login
	 * 
	 * @param request
	 * @return - Redirects the browser to the final LOGOUT template.
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return AuthenticationController.REDIRECT_LOGIN;
	}
}
