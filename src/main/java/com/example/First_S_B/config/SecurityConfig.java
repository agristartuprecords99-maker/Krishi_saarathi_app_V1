package com.example.First_S_B.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration for Krishi Saarathi Application
 *
 * This class configures Spring Security settings.
 * Currently set to permit all requests for development phase.
 * Later we'll implement JWT-based authentication for production.
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure HTTP Security
     *
     * This method configures how Spring Security handles HTTP requests.
     * For development, we're allowing all requests without authentication.
     *
     * @param http HttpSecurity configuration object
     * @return SecurityFilterChain the configured security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configure HTTP security
        http
                // Disable CSRF protection (not needed for REST APIs with JWT)
                .csrf(csrf -> csrf.disable())

                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests without authentication (for development)
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
