/**
 * 
 */
package com.example.BasicProjectTemplate.web.form;


import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
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

	@Nonnull
	@Size(min=3, max=30)
	private String name;

	@NotNull
	@Min(18)
	private Integer age;


}
