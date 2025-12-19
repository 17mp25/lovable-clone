package com.mp.projects.lovable_clone.repository;

import com.mp.projects.lovable_clone.entity.ProjectMember;
import com.mp.projects.lovable_clone.entity.ProjectMemberId;
import com.mp.projects.lovable_clone.enums.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    List<ProjectMember> findByIdProjectIdAndInviteStatus(Long projectId, InviteStatus inviteStatus);


    Optional<ProjectMember> findByIdProjectIdAndIdUserId(Long projectMemberId, Long userId);
}
