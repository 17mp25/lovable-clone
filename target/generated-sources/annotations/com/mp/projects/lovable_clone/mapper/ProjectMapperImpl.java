package com.mp.projects.lovable_clone.mapper;

import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.mp.projects.lovable_clone.dto.project.ProjectResponse;
import com.mp.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.mp.projects.lovable_clone.entity.Project;
import com.mp.projects.lovable_clone.entity.User;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T19:35:07+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponse toProjectResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        UserProfileResponse owner = null;

        id = project.getId();
        name = project.getName();
        createdAt = project.getCreatedAt();
        updatedAt = project.getUpdatedAt();
        owner = userToUserProfileResponse( project.getOwner() );

        ProjectResponse projectResponse = new ProjectResponse( id, name, createdAt, updatedAt, owner );

        return projectResponse;
    }

    @Override
    public ProjectSummaryResponse toProjectSummaryResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = project.getId();
        name = project.getName();
        createdAt = project.getCreatedAt();
        updatedAt = project.getUpdatedAt();

        ProjectSummaryResponse projectSummaryResponse = new ProjectSummaryResponse( id, name, createdAt, updatedAt );

        return projectSummaryResponse;
    }

    @Override
    public List<ProjectSummaryResponse> toListProjectSummaryResponse(List<Project> project) {
        if ( project == null ) {
            return null;
        }

        List<ProjectSummaryResponse> list = new ArrayList<ProjectSummaryResponse>( project.size() );
        for ( Project project1 : project ) {
            list.add( toProjectSummaryResponse( project1 ) );
        }

        return list;
    }

    protected UserProfileResponse userToUserProfileResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String name = null;
        String avatarUrl = null;

        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        avatarUrl = user.getAvatarUrl();

        UserProfileResponse userProfileResponse = new UserProfileResponse( id, email, name, avatarUrl );

        return userProfileResponse;
    }
}
