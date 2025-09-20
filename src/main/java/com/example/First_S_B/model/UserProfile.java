package com.example.First_S_B.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * UserProfile Entity for storing extended user information
 *
 * This entity contains additional profile information that's not essential
 * for authentication but useful for the application functionality:
 * - Address and location details
 * - Profile image
 * - Role-specific information in JSON format
 *
 * @author Krishi Saarathi Team
 * @version 1.0.0
 */
@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    /**
     * Primary key for the UserProfile entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * One-to-One relationship with User entity
     * Each user has exactly one profile
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * User's address (street, house number, etc.)
     */
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    /**
     * User's city
     */
    @Column(name = "city", length = 100)
    private String city;

    /**
     * User's state/province
     */
    @Column(name = "state", length = 100)
    private String state;

    /**
     * User's postal/pin code
     */
    @Column(name = "pincode", length = 10)
    private String pincode;

    /**
     * URL to user's profile image
     * Can be stored in cloud storage or local file system
     */
    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    /**
     * Additional information stored in JSON format
     * This allows flexibility for role-specific data:
     * - Farmers: crop preferences, land area, farming experience
     * - Drivers: vehicle details, license info, service areas
     * - Markets: business details, product categories, operating hours
     */
    @Column(name = "additional_info", columnDefinition = "JSONB")
    private String additionalInfo;

    /**
     * Timestamp when this profile was created
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when this profile was last updated
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Automatically set timestamps before persist operation
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Automatically update timestamp before update operation
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
