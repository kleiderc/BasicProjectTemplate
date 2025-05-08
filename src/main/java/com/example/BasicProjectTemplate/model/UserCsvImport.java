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
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Table(name = "usercsvimport", schema = "basicprojecttemplate")
@NoArgsConstructor
@Data
public class UserCsvImport {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String lastName;
    
    private String firstName;
    
    private String position;
    
    private Integer birthYear;
    
}
