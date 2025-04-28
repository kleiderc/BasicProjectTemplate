/**
 * 
 */
package com.example.BasicProjectTemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.BasicProjectTemplate.dto.UserForm;
import com.example.BasicProjectTemplate.model.User;
import com.example.BasicProjectTemplate.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service layer (Business Logic layer) for user operations.
 */
@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public User findById(final Integer id) {
		log.debug("Fetching user with ID: {}", id);
		User user = userRepository.findById(id.longValue()).orElse(null);
		if (user == null) {
			log.error("User not found with ID: {}", id);
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

		log.info("Creating user with ID: {}", user.getId());
		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}