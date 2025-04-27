/**
 * 
 */
package com.example.BasicProjectTemplate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 */
@Entity
@Table(name = "authentication", schema = "basicprojecttemplate")
@Data
public class Authentication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String userId;
	private String password;

	// Getters and setters
}
