package com.example.BasicProjectTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BasicProjectTemplate.model.Authentication;

/**
 * Provides basic CRUD operations using JpaRepository
 */
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
	
	// SELECT * FROM user WHERE UserId = ?
	Authentication findByUserId(String userId);
}