package com.example.BasicProjectTemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.BasicProjectTemplate.web.form.UserForm;

import jakarta.validation.Valid;

/**
 * http://localhost:8080/index.html
 * 
 */
@Controller
@RequestMapping("/user")
public class UserFormValidationController implements WebMvcConfigurer {
	
	private static final String UPDATE_VIEW = "userform/update";
	private static final String READ_VIEW = "userform/read";
	private static final String REDIRECT = "redirect:/userform/read";
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(UserFormValidationController.READ_VIEW).setViewName(UserFormValidationController.READ_VIEW);
	}

	/**
	 * http://localhost:8080/user/read
	 * 
	 * The showForm method returns the UPDATE_VIEW template. 
	 * 
	 * @param userForm - The userForm in its method signature that the template can associate form attributes with a userForm.
	 * @return
	 */
	@GetMapping("/read")
	public String showForm(UserForm userForm) {
		return UserFormValidationController.UPDATE_VIEW;
	}

	/**
	 * 
	 * 
	 * @param userForm - A userForm object marked with @Valid to gather the attributes filled out in the form.
	 * @param bindingResult - A bindingResult object so that you can test for and retrieve validation errors.
	 * @return If all of the person’s attribute are valid, it redirects the browser to the final REDIRECT template.
	 */
	@PostMapping("/update")
	public String checkPersonInfo(@Valid UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			// http://localhost:8080/user/update
			return UserFormValidationController.UPDATE_VIEW;
		}
		//http://localhost:8080/userform/read
		return UserFormValidationController.REDIRECT;
	}

}
