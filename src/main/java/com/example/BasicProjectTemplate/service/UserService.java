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

/**
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public User findById(final Integer id) {
		return userRepository.findById(id.longValue()).orElse(null);
	}

	public User save(final UserForm userForm) {
		final User user = new User();
		user.setId(userForm.getId());
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setAge(userForm.getAge());

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}