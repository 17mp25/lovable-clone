package com.mp.projects.lovable_clone.controller;

import com.mp.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.mp.projects.lovable_clone.dto.member.MemberResponse;
import com.mp.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.mp.projects.lovable_clone.service.ProjectMemberService;
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
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId, @RequestBody InviteMemberRequest request) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.inviteMember(projectId, request, userId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody UpdateMemberRoleRequest request) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request, userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeProjectMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        Long userId = 1L;
        projectMemberService.removeProjectMember(projectId, memberId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/invitee")
    public ResponseEntity<MemberResponse> getMyInvite(@PathVariable Long projectId) {
        Long userId = 3L;
        return ResponseEntity.ok(projectMemberService.getMyInvite(projectId, userId));
    }

    @PostMapping("/invite/accept")
    public ResponseEntity<MemberResponse> acceptInvite(@PathVariable Long projectId){
        Long userId = 3L;
        return ResponseEntity.ok(projectMemberService.acceptInvite(projectId,userId));
    }

}
