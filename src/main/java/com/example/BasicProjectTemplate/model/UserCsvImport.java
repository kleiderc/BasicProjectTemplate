/**
 * 
 */
package com.example.BasicProjectTemplate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@NoArgsConstructor
@Data
public class UserCsvImport {

	private String id;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    
}
