package com.mp.projects.lovable_clone.service;

import com.mp.projects.lovable_clone.dto.auth.AuthResponse;
import com.mp.projects.lovable_clone.dto.auth.LoginRequest;
import com.mp.projects.lovable_clone.dto.auth.SignUpRequest;

public interface AuthService {
    AuthResponse signUp(SignUpRequest request);

    AuthResponse login(LoginRequest request);
}
