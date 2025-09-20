package com.example.First_S_B.controller;

import com.example.First_S_B.model.Role;
import com.example.First_S_B.model.User;
import com.example.First_S_B.repository.RoleRepository;
import com.example.First_S_B.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST Controller for User Management
 *
 * This controller provides endpoints for user registration, retrieval,
 * and management across different roles (ADMIN, FARMER, DRIVER, MARKET).
 *
 * Base URL: /api/v1/users
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Allow Flutter app to access
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * GET /api/v1/users/roles
     * Get all available roles in the system
     *
     * @return List of all roles with their details
     */
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    /**
     * POST /api/v1/users/register
     * Register a new user with specified role
     *
     * @param userRequest JSON object with user details
     * @return Created user or error message
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Map<String, String> userRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Validate required fields
            String email = userRequest.get("email");
            String password = userRequest.get("password");
            String firstName = userRequest.get("firstName");
            String roleName = userRequest.get("role");

            if (email == null || password == null || firstName == null || roleName == null) {
                response.put("success", false);
                response.put("message", "Missing required fields: email, password, firstName, role");
                return ResponseEntity.badRequest().body(response);
            }

            // Check if email already exists
            if (userRepository.existsByEmail(email)) {
                response.put("success", false);
                response.put("message", "Email already registered");
                return ResponseEntity.badRequest().body(response);
            }

            // Find the role
            Optional<Role> roleOpt = roleRepository.findByRoleName(roleName.toUpperCase());
            if (!roleOpt.isPresent()) {
                response.put("success", false);
                response.put("message", "Invalid role. Must be: ADMIN, FARMER, DRIVER, or MARKET");
                return ResponseEntity.badRequest().body(response);
            }

            // Create new user
            User user = new User();
            user.setEmail(email);
            user.setPassword(password); // In production, hash the password
            user.setFirstName(firstName);
            user.setLastName(userRequest.get("lastName"));
            user.setPhoneNumber(userRequest.get("phoneNumber"));
            user.setRole(roleOpt.get());
            user.setIsActive(true);
            user.setIsVerified(false);

            // Save user
            User savedUser = userRepository.save(user);

            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("userId", savedUser.getId());
            response.put("email", savedUser.getEmail());
            response.put("role", savedUser.getRole().getRoleName());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * GET /api/v1/users
     * Get all users (for admin purposes)
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * GET /api/v1/users/role/{roleName}
     * Get all users by role
     *
     * @param roleName Role name (ADMIN, FARMER, DRIVER, MARKET)
     */
    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String roleName) {
        List<User> users = userRepository.findByRoleName(roleName.toUpperCase());
        return ResponseEntity.ok(users);
    }

    /**
     * GET /api/v1/users/stats
     * Get user statistics by role
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalUsers", userRepository.count());
        stats.put("adminCount", userRepository.countByRoleName("ADMIN"));
        stats.put("farmerCount", userRepository.countByRoleName("FARMER"));
        stats.put("driverCount", userRepository.countByRoleName("DRIVER"));
        stats.put("marketCount", userRepository.countByRoleName("MARKET"));

        return ResponseEntity.ok(stats);
    }
}
