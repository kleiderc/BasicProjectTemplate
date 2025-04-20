/**
 * 
 */
package com.example.BasicProjectTemplate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * When invoked from a browser, the method returns pure text. 
 * That is because @RestController combines @Controller and @ResponseBody, 
 * two annotations that results in web requests returning data rather than a view.
 */
@RestController
public class GreetingRestController {

	/**
	 * http://localhost:8080/greetingrest
	 * 
	 * @return "Greetings from Spring Boot!" 
	 */
	@GetMapping("/greetingrest") // @GetMapping maps / to the greetingRest() method
	public String greetingRest() {
		return "Greetings from Spring Boot!";
	}
		
}
