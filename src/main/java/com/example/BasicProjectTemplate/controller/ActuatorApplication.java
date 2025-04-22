/**
 * 
 */
package com.example.BasicProjectTemplate.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:9000/
 * http://localhost:9000/status
 * http://localhost:9001/actuator/info
 * http://localhost:9001/actuator/health
 * http://localhost:9001/actuator/metrics
 */
@RestController
public class ActuatorApplication {

	@GetMapping("/")
    public String home() {
        return "Welcome to Actuator Demo!";
    }

    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of("status", "Application is running fine!");
    }

}
