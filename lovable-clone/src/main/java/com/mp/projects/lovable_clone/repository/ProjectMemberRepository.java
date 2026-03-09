package com.mp.projects.lovable_clone.repository;

import com.mp.projects.lovable_clone.entity.ProjectMember;
import com.mp.projects.lovable_clone.entity.ProjectMemberId;
import com.mp.projects.lovable_clone.enums.InviteStatus;
import com.mp.projects.lovable_clone.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    List<ProjectMember> findByIdProjectIdAndInviteStatus(Long projectId, InviteStatus inviteStatus);


    Optional<ProjectMember> findByIdProjectIdAndIdUserId(Long projectMemberId, Long userId);


    @Query(
            """
                    SELECT pm.projectRole from ProjectMember pm 
                    WHERE pm.id.projectId = :projectId AND pm.id.userId=:userId
                    """
    )
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,
                                                       @Param("userId") Long userId);
}
