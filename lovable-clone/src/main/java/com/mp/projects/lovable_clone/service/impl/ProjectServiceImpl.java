package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.project.ProjectRequest;
import com.mp.projects.lovable_clone.dto.project.ProjectResponse;
import com.mp.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.mp.projects.lovable_clone.entity.Project;
import com.mp.projects.lovable_clone.entity.User;
import com.mp.projects.lovable_clone.mapper.ProjectMapper;
import com.mp.projects.lovable_clone.repository.ProjectRepository;
import com.mp.projects.lovable_clone.repository.UserRepository;
import com.mp.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        Project save = projectRepository.save(project);
        return projectMapper.toProjectResponse(save);

    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
//        return projectRepository.findAllAccessibleUser(userId)
//        .stream().map(projectMapper::toProjectSummaryResponse)
//        .collect(Collectors.toList());

      var allAccessibleUser = projectRepository.findAllAccessibleUser(userId);
        return projectMapper.toListProjectSummaryResponse(allAccessibleUser);


    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public Void softDelete(Long id, Long userId) {
        return null;
    }
}
