package com.mp.projects.lovable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.mp.projects.lovable_clone.enums.ProjectPermissions.*;

/**
 * Enum representing the role of a user within a project.
 * Determines the level of access and permissions a user has.
 */
@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(VIEW, EDIT, DELETE, VIEW_MEMBERS),
    VIEWER(VIEW, VIEW_MEMBERS),
    OWNER(VIEW, EDIT, DELETE, VIEW_MEMBERS, MANAGE_MEMBERS);

    private final Set<ProjectPermissions> pemissions;

    private ProjectRole(ProjectPermissions... permissions) {
        this.pemissions = Set.of(permissions);
    }
}
