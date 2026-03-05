package com.mp.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 * Represents a user of the Lovable application.
 * Stores login, profile, and auditing information.
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Used to make all the fields as Private to achieve encapsulation
@Table(name = "users")
public class User implements UserDetails// User/Person who is using the Lovable application
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    // Primary key for the user.
    // Unique identifier used to link with other tables (e.g., Project, ChatSession)

    String username;
    // User's email address.
    // Typically unique and used as login identifier

    String password;
    // Hashed password for authentication.
    // Never store plain-text passwords.

    String name;
    // Full name or display name of the user

    @CreationTimestamp
    Instant createdAt;
    // Timestamp when the user account was created
    @UpdateTimestamp
    Instant updatedAt;
    // Timestamp when the user's profile was last updated

    Instant deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    // Soft delete timestamp.
    // If not null → user is considered deleted (inactive) but record remains in DB
}


