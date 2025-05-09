package com.example.BasicProjectTemplate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BasicProjectTemplate.dto.UserForm;
import com.example.BasicProjectTemplate.model.User;
import com.example.BasicProjectTemplate.service.UserService;

import jakarta.validation.Valid;

/**
 * http://localhost:8080/index.html
 * 
 */
//@Slf4j // TO use log instead of logger
@Controller
@RequestMapping("/user") // Base route for all user operations
public class UserController {

	private static final String CREATE_VIEW = "userdto/create";
	private static final String READ_VIEW = "userdto/read";
	private static final String UPDATE_VIEW = "userdto/update";

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * http://localhost:8080/user
	 * 
	 * The showForm method returns the CREATE_VIEW template.
	 * 
	 * @param userForm - The userForm in its method signature that the template can
	 *                 associate form attributes with a userForm.
	 * @return
	 */
	@GetMapping
	public String showForm(UserForm userForm) {
		
		logger.info("Received request to show the Create template");
		return UserController.CREATE_VIEW; // Returns the view name: create.html
	}

	/**
	 * 
	 * 
	 * @param userForm
	 * @param bindingResult
	 * @param model         - An interface used to pass data from the controller to
	 *                      the view (like Thymeleaf HTML pages).
	 * @return - Redirects the browser to the final READ template.
	 */
	@PostMapping("/create")
	public String createUser(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
		
		logger.info("Received request to create user: {}", userForm);
		if (bindingResult.hasErrors()) {
			// http://localhost:8080/user/create
			return UserController.CREATE_VIEW;// Returns the view name: create.html
		}

		userService.save(userForm);

		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users); // Pass data to the view

		// http://localhost:8080/userform/read
		return UserController.READ_VIEW; // Returns the view name: read.html
	}

	/**
	 * Display all users
	 * 
	 * @param model - An interface used to pass data from the controller to the view
	 *              (like Thymeleaf HTML pages).
	 * @return - Redirects the browser to the final READ template.
	 */
	@GetMapping("/read")
	public String listUsers(Model model) {

		logger.info("Received request to list all users");
		
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users); // Pass data to the view

		return UserController.READ_VIEW; // Returns the view name: read.html
	}

	/**
	 * 
	 * 
	 * @param userForm      - A userForm object marked with @Valid to gather the
	 *                      attributes filled out in the form.
	 * @param bindingResult - A bindingResult object so that you can test for and
	 *                      retrieve validation errors.
	 * @param model         - An interface used to pass data from the controller to
	 *                      the view (like Thymeleaf HTML pages).
	 * @return If all of the person’s attribute are valid, it redirects the browser
	 *         to the final READ template.
	 */
	@PostMapping("/update")
	public String updateUser(@Valid UserForm userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			// http://localhost:8080/user/update
			return UserController.UPDATE_VIEW; // Returns the view name: update.html
		}

		userService.save(userForm);

		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users); // Pass data to the view

		// http://localhost:8080/userform/read
		return UserController.READ_VIEW; // Returns the view name: read.html
	}

	/**
	 * 
	 * @param id
	 * @param model - An interface used to pass data from the controller to the view
	 *              (like Thymeleaf HTML pages).
	 * @return - Redirects the browser to the final UPDATE template.
	 */
	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model) {

		logger.info("Received request to update user with ID: {}", id);
		
		User user = userService.findUserById(id);
		model.addAttribute("userForm", user);// Pass data to the view

		return UserController.UPDATE_VIEW; // Returns the view name: update.html
	}

	/**
	 * 
	 * @param id
	 * @param model - An interface used to pass data from the controller to the view
	 *              (like Thymeleaf HTML pages).
	 * @return - Redirects the browser to the final READ template.
	 */
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {

		logger.info("Received request to delete user with ID: {}", id);
		
		userService.deleteUser(id);

		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users); // Pass data to the view

		return UserController.READ_VIEW; // Returns the view name: read.html
	}
	
	/**
	 * 
	 * http://localhost:8080/user/8
	 * 
	 * @param id
	 * @param model - An interface used to pass data from the controller to the view
	 *              (like Thymeleaf HTML pages).
	 * @return - Redirects the browser to the final UPDATE template.
	 */
	@GetMapping("/{id}")
	public String getUserById(@PathVariable Integer id, Model model) {

		logger.info("Received request for user with id: {}", id);

		User user = userService.findUserById(id);
		model.addAttribute("userForm", user);// Pass data to the view

		return UserController.UPDATE_VIEW; // Returns the view name: update.html
	}

}
