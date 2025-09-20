package com.example.First_S_B.repository;

import com.example.First_S_B.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity
 *
 * This interface provides CRUD operations for User entities
 * with custom query methods for authentication and user management.
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by email address
     *
     * This is the primary method for user authentication.
     * Email is unique in the system, so returns Optional.
     *
     * @param email user's email address
     * @return Optional<User> - user if found, empty if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by email and check if active
     *
     * This is useful for login validation - only active users can login
     *
     * @param email user's email address
     * @param isActive whether the user is active
     * @return Optional<User> - active user if found, empty otherwise
     */
    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);

    /**
     * Check if email already exists in the system
     *
     * Useful for user registration validation
     *
     * @param email email to check
     * @return boolean - true if exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Find all users by role name
     *
     * This method uses a JPQL query to join with Role entity
     *
     * @param roleName name of the role (ADMIN, FARMER, DRIVER, MARKET)
     * @return List<User> - all users with the specified role
     */
    @Query("SELECT u FROM User u JOIN u.role r WHERE r.roleName = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);

    /**
     * Find all active users by role name
     *
     * Returns only active users with a specific role
     *
     * @param roleName name of the role
     * @param isActive whether users are active
     * @return List<User> - active users with the specified role
     */
    @Query("SELECT u FROM User u JOIN u.role r WHERE r.roleName = :roleName AND u.isActive = :isActive")
    List<User> findActiveUsersByRoleName(@Param("roleName") String roleName, @Param("isActive") Boolean isActive);

    /**
     * Count users by role name
     *
     * Useful for dashboard statistics
     *
     * @param roleName name of the role
     * @return long - number of users with the specified role
     */
    @Query("SELECT COUNT(u) FROM User u JOIN u.role r WHERE r.roleName = :roleName")
    long countByRoleName(@Param("roleName") String roleName);
}
