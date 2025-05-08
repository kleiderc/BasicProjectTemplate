/**
 * 
 */
package com.example.BasicProjectTemplate.dto;

import java.time.Year;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@NoArgsConstructor
@Data
public class UserCsvImportForm {

	private Integer id;
    private String lastName;
    private String firstName;
    private String position;
    private Integer birthYear;
    
}
