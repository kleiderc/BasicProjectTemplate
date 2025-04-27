/**
 * 
 */
package com.example.BasicProjectTemplate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to register all interceptors.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * Register interceptors and define their order.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AuthInterceptor runs first (security-related)
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")      // Apply only for specific paths or register the AuthInterceptor for all paths
                .excludePathPatterns("/api/public/**"); // Skip auth for public APIs

        // LoggingInterceptor runs for all paths
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**");          // Apply to all paths
    }
}