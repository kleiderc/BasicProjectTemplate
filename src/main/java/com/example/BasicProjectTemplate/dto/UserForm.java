/**
 * 
 */
package com.example.BasicProjectTemplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserForm {

	private Integer id;

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 30)
	private String name;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	private String email;

	@Min(value = 18, message = "Age must be at least 18")
	@Max(value = 100, message = "Age must be less than or equal to 100")
	@NotNull
	private Integer age;

}
