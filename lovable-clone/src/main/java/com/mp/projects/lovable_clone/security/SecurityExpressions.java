package com.mp.projects.lovable_clone.security;

import com.mp.projects.lovable_clone.enums.ProjectPermissions;
import com.mp.projects.lovable_clone.enums.ProjectRole;
import com.mp.projects.lovable_clone.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {

    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    private boolean hasaPermission(Long projectId, ProjectPermissions permissions) {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
                .map(role -> role.getPemissions().contains(permissions)).orElse(false);
    }

    public boolean canViewProject(Long projectId) {
        // Implement logic to check if the authenticated user can view the project with the given ID
        // This might involve checking if the user is the owner of the project or has been granted access
        Long userId = authUtil.getCurrentUserId();
        return hasaPermission(projectId, ProjectPermissions.VIEW);
    }

    public boolean canEditProject(Long projectId) {
        // Implement logic to check if the authenticated user can edit the project with the given ID
        // This might involve checking if the user is the owner of the project
        Long userId = authUtil.getCurrentUserId();
        return hasaPermission(projectId, ProjectPermissions.EDIT);
    }

    public boolean canDeleteProject(Long projectId) {
        // Implement logic to check if the authenticated user can delete the project with the given ID
        // This might involve checking if the user is the owner of the project
        Long userId = authUtil.getCurrentUserId();
        return hasaPermission(projectId, ProjectPermissions.DELETE);
    }

    public boolean canViewMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasaPermission(projectId, ProjectPermissions.VIEW_MEMBERS);
    }

    public boolean canManageMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return hasaPermission(projectId, ProjectPermissions.MANAGE_MEMBERS);
    }
}
