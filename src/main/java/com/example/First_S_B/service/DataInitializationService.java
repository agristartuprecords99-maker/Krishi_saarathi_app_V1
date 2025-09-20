package com.example.First_S_B.service;

import com.example.First_S_B.model.Role;
import com.example.First_S_B.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Service to initialize default data in the database
 *
 * This service runs when the application starts and creates
 * the default roles required for RBAC system.
 *
 * Implements CommandLineRunner to execute after application context is loaded.
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * This method runs when the application starts
     *
     * Creates the four main roles for Krishi Saarathi:
     * 1. ADMIN - System administrators
     * 2. FARMER - Agricultural producers
     * 3. DRIVER - Transportation providers
     * 4. MARKET - Product vendors
     *
     * @param args command line arguments (not used)
     */
    @Override
    public void run(String... args) {
        System.out.println("🌱 Initializing default data...");

        createRoleIfNotExists("ADMIN", "System Administrator with full platform access");
        createRoleIfNotExists("FARMER", "Agricultural producers using farming services");
        createRoleIfNotExists("DRIVER", "Transportation providers for agricultural logistics");
        createRoleIfNotExists("MARKET", "Product vendors managing sales and inventory");

        System.out.println("✅ Default data initialization completed!");

        // Display role statistics
        displayRoleStatistics();
    }

    /**
     * Create a role if it doesn't already exist
     *
     * @param roleName name of the role to create
     * @param description description of the role's purpose
     */
    private void createRoleIfNotExists(String roleName, String description) {
        if (!roleRepository.existsByRoleName(roleName)) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setDescription(description);
            roleRepository.save(role);
            System.out.println("📝 Created role: " + roleName);
        } else {
            System.out.println("✓ Role already exists: " + roleName);
        }
    }

    /**
     * Display current role statistics in the console
     */
    private void displayRoleStatistics() {
        long totalRoles = roleRepository.count();
        System.out.println("\n📊 ROLE STATISTICS:");
        System.out.println("Total roles in system: " + totalRoles);

        // Display each role with its ID
        roleRepository.findAll().forEach(role ->
                System.out.println("  - " + role.getRoleName() + " (ID: " + role.getId() + ")")
        );
        System.out.println();
    }
}
