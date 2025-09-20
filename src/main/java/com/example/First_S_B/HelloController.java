package com.example.First_S_B;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Home page: http://localhost:8080/
    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot is running!";
    }

    // Test endpoint: http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
