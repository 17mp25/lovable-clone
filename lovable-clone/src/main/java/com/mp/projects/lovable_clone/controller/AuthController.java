package com.mp.projects.lovable_clone.controller;

import com.mp.projects.lovable_clone.dto.auth.AuthResponse;
import com.mp.projects.lovable_clone.dto.auth.LoginRequest;
import com.mp.projects.lovable_clone.dto.auth.SignUpRequest;
import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.mp.projects.lovable_clone.service.AuthService;
import com.mp.projects.lovable_clone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signUp(request));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));

    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = 1L;//During Spring Security we will get it from Context holder
        return ResponseEntity.ok(userService.getProfile());
    }
}
