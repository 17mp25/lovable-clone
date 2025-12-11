package com.mp.projects.lovable_clone.entity;

import com.mp.projects.lovable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * Represents a user's membership inside a project.
 * Stores the relationship between Project and User,
 * along with the role and invitation details.
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;
    // Composite key (projectId + userId)
    // Ensures the same user cannot be added twice to the same project
    @ManyToOne
    @MapsId("projectId")
    Project project;
    // The project to which the user is added as a member
    @ManyToOne
    @MapsId("userId")
    User user;
    // The user who is part of the project
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    ProjectRole role;
    // Role assigned to the member inside the project
    // e.g., OWNER, EDITOR, VIEWER

    Instant invitedAt;
    // Timestamp when the user was invited to join the project

    Instant acceptedAt;
    // Timestamp when the user accepted the invitation
    // (null means invitation is pending/not accepted yet)


}
