/**
 * 
 */
package com.example.BasicProjectTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BasicProjectTemplate.model.User;

/**
 * Provides basic CRUD operations using JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}