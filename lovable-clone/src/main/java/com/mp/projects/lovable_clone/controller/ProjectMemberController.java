package com.mp.projects.lovable_clone.controller;

import com.mp.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.mp.projects.lovable_clone.dto.member.MemberResponse;
import com.mp.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.mp.projects.lovable_clone.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId,
                                                       @RequestBody @Valid InviteMemberRequest request)
    {
        return ResponseEntity.ok(projectMemberService.inviteMember(projectId, request));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long projectId, @PathVariable Long memberId,
                                                           @RequestBody @Valid UpdateMemberRoleRequest request)
    {
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeProjectMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        projectMemberService.removeProjectMember(projectId, memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/invitee")
    public ResponseEntity<MemberResponse> getMyInvite(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.getMyInvite(projectId));
    }

    @PostMapping("/invite/accept")
    public ResponseEntity<MemberResponse> acceptInvite(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.acceptInvite(projectId));
    }

    @PostMapping("/invite/reject")
    public ResponseEntity<MemberResponse> rejectInvite(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.rejectInvite(projectId));
    }

}
