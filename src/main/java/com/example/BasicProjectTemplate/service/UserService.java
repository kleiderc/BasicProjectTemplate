/**
 * 
 */
package com.example.BasicProjectTemplate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.BasicProjectTemplate.dto.UserForm;
import com.example.BasicProjectTemplate.model.User;
import com.example.BasicProjectTemplate.repository.UserRepository;

/**
 * Service layer (Business Logic layer) for user operations.
 */
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public User findUserById(final Integer id) {
		
		logger.debug("Fetching user with ID: {}", id);
		
		User user = userRepository.findById(id.longValue()).orElse(null);
		if (user == null) {
			logger.error("User not found with ID: {}", id);
			throw new RuntimeException("User not found!");
		}
		
		return user;
	}

	public User save(final UserForm userForm) {
		
		final User user = new User();
		user.setId(userForm.getId());
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setAge(userForm.getAge());

		logger.debug("Creating user with ID: {}", user.getId());
		
		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		
		logger.debug("Deleting user with ID: {}", id);
		
		userRepository.deleteById(id);
	}

}