package com.example.BasicProjectTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BasicProjectTemplate.model.Authentication;
import com.example.BasicProjectTemplate.repository.AuthenticationRepository;

/**
 * Service layer (Business Logic layer) for user operations.
 */
@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationRepository authenticationRepository;

	public Authentication findByUserId(final String userId) {
		return authenticationRepository.findByUserId(userId);
	}

	// Authenticate user
	public boolean authenticate(String userId, String password) {

		final Authentication authentication = findByUserId(userId);

		boolean authenticate = (authentication == null) ? false
				: authentication.getUserId().equals(userId) && authentication.getPassword().equals(password);

		return authenticate;
	}
}
