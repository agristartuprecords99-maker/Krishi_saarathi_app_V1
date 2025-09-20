package com.example.First_S_B.repository;

import com.example.First_S_B.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity
 *
 * This interface provides CRUD operations for Role entities.
 * Spring Data JPA automatically implements these methods.
 *
 * Key features:
 * - Basic CRUD operations (save, find, delete, etc.)
 * - Custom query method to find role by name
 * - Transaction management handled by Spring
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find a role by its name (ADMIN, FARMER, DRIVER, MARKET)
     *
     * This method uses Spring Data JPA query derivation.
     * Spring automatically generates the implementation based on method name.
     *
     * @param roleName the name of the role to find
     * @return Optional<Role> - role if found, empty if not found
     */
    Optional<Role> findByRoleName(String roleName);

    /**
     * Check if a role exists by role name
     *
     * Useful for validation before creating new roles
     *
     * @param roleName the name of the role to check
     * @return boolean - true if exists, false otherwise
     */
    boolean existsByRoleName(String roleName);
}
