package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.mp.projects.lovable_clone.dto.member.MemberResponse;
import com.mp.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.mp.projects.lovable_clone.service.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fetch project members visible to the logged-in user.
 * <p>
 * Business Logic to implement:
 * 1. Validate the project exists.
 * 2. Check if userId is part of the project (authorization).
 * 3. Fetch all members from project_member table.
 * 4. Map entity -> MemberResponse DTO.
 * 5. Return the list.
 */
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse deleteMember(Long projectId, Long memberId, Long userId) {
        return null;
    }
}
