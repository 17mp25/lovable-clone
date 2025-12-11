package com.mp.projects.lovable_clone.repository;

import com.mp.projects.lovable_clone.entity.ProjectMember;
import com.mp.projects.lovable_clone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember , ProjectMemberId> {
}
