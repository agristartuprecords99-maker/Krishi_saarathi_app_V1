package com.example.First_S_B.repository;

import com.example.First_S_B.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for UserProfile entity
 *
 * This interface provides CRUD operations for user profile information.
 * Each user has exactly one profile with extended information.
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    /**
     * Find user profile by user ID
     *
     * Since there's a one-to-one relationship between User and UserProfile,
     * this method finds the profile for a specific user.
     *
     * @param userId the ID of the user
     * @return Optional<UserProfile> - profile if found, empty otherwise
     */
    Optional<UserProfile> findByUserId(Long userId);

    /**
     * Delete user profile by user ID
     *
     * Useful when deleting a user account
     *
     * @param userId the ID of the user whose profile should be deleted
     */
    void deleteByUserId(Long userId);
}
