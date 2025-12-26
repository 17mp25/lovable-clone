package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.mp.projects.lovable_clone.dto.member.MemberResponse;
import com.mp.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.mp.projects.lovable_clone.entity.Project;
import com.mp.projects.lovable_clone.entity.ProjectMember;
import com.mp.projects.lovable_clone.entity.ProjectMemberId;
import com.mp.projects.lovable_clone.entity.User;
import com.mp.projects.lovable_clone.enums.InviteStatus;
import com.mp.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.mp.projects.lovable_clone.repository.ProjectMemberRepository;
import com.mp.projects.lovable_clone.repository.ProjectRepository;
import com.mp.projects.lovable_clone.repository.UserRepository;
import com.mp.projects.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponseList.addAll(projectMemberRepository.findByIdProjectIdAndInviteStatus(projectId, InviteStatus.ACCEPTED)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember).toList());
        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }

        User invitee = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        ProjectMember projectMember = projectMemberRepository
                .findById(projectMemberId)
                .orElse(null);

        if (projectMember != null) {
            if (projectMember.getInviteStatus() == InviteStatus.PENDING) {
                throw new RuntimeException("Invite already sent");
            }
            if (projectMember.getInviteStatus() == InviteStatus.ACCEPTED) {
                throw new RuntimeException("User already a member");
            }

            // REJECTED → re-invite
            projectMember.setInviteStatus(InviteStatus.PENDING);
            projectMember.setInvitedAt(Instant.now());
            projectMember.setProjectRole(request.role());

            projectMemberRepository.save(projectMember);
            return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
        }

        // Fresh invite
        ProjectMember newMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .inviteStatus(InviteStatus.PENDING)
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(newMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(newMember);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        if (projectMember.getInviteStatus() != InviteStatus.ACCEPTED) {
            throw new RuntimeException("Cannot update role for non-active member");
        }
        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if (!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("This User is not a member of this Project");
        }
        if (memberId.equals(project.getOwner().getId())) {
            throw new RuntimeException("Owner cannot be removed");
        }
        projectMemberRepository.deleteById(projectMemberId);

    }

    @Override
    public MemberResponse getMyInvite(Long projectId, Long userId) {
        ProjectMember projectMember = projectMemberRepository.findByIdProjectIdAndIdUserId(projectId, userId).orElseThrow(() -> new RuntimeException("Invite not found"));

        if (projectMember.getInviteStatus() != InviteStatus.PENDING) {
            throw new RuntimeException("No pending Invite");
        }

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse acceptInvite(Long projectId, Long userId) {
        ProjectMember projectMember = projectMemberRepository.findByIdProjectIdAndIdUserId(projectId, userId).orElseThrow(() -> new RuntimeException("Invite not found"));

        if (projectMember.getInviteStatus() == InviteStatus.ACCEPTED) {
            throw new RuntimeException("Invite already accepted");
        }
        if (projectMember.getInviteStatus() == InviteStatus.REJECTED) {
            throw new RuntimeException("Invite was rejected, ask owner to re-invite");
        }

        projectMember.setInviteStatus(InviteStatus.ACCEPTED);
        projectMember.setAcceptedAt(Instant.now());

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse rejectInvite(Long projectId, Long userId) {
        ProjectMember projectMember = projectMemberRepository.findByIdProjectIdAndIdUserId(projectId, userId).orElseThrow(() -> new RuntimeException("Invite not found"));

        if(projectMember.getInviteStatus()!=InviteStatus.PENDING){
            throw new RuntimeException("Invite Already Rejected");
        }

        projectMember.setInviteStatus(InviteStatus.REJECTED);
        projectMember.setAcceptedAt(null);
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
