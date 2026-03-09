package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.project.ProjectRequest;
import com.mp.projects.lovable_clone.dto.project.ProjectResponse;
import com.mp.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.mp.projects.lovable_clone.entity.Project;
import com.mp.projects.lovable_clone.entity.ProjectMember;
import com.mp.projects.lovable_clone.entity.ProjectMemberId;
import com.mp.projects.lovable_clone.entity.User;
import com.mp.projects.lovable_clone.enums.InviteStatus;
import com.mp.projects.lovable_clone.enums.ProjectRole;
import com.mp.projects.lovable_clone.error.ResourceNotFoundException;
import com.mp.projects.lovable_clone.mapper.ProjectMapper;
import com.mp.projects.lovable_clone.repository.ProjectMemberRepository;
import com.mp.projects.lovable_clone.repository.ProjectRepository;
import com.mp.projects.lovable_clone.repository.UserRepository;
import com.mp.projects.lovable_clone.security.AuthUtil;
import com.mp.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
//        User owner = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User",
//                userId.toString()));

        User owner = userRepository.getReferenceById(
                userId);// It wont make a DB call immediately, it will return a proxy object. It will make a DB call only when we access any property of the user object. Since we are using the user object to set the project member, it will make a DB call at that time. So we can use getReferenceById to avoid unnecessary DB call if we are not accessing any property of the user object.

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();
        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(),
                owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .id(projectMemberId)
                .project(project)
                .inviteStatus(InviteStatus.ACCEPTED)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);
        return projectMapper.toProjectResponse(project);

    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        var allAccessibleUser = projectRepository.findAllAccessibleUser(userId);
        return projectMapper.toListProjectSummaryResponse(allAccessibleUser);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#id)")
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#id)")
    public ProjectResponse updateProject(Long id, ProjectRequest request)
    {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        project.setName(request.name());
        Project save = projectRepository.save(project);
        return projectMapper.toProjectResponse(save);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#id)")
    public void softDelete(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project",
                        projectId.toString()));
    }
}
