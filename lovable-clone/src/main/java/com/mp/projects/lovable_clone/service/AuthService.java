package com.mp.projects.lovable_clone.service;

import com.mp.projects.lovable_clone.dto.auth.AuthResponse;
import com.mp.projects.lovable_clone.dto.auth.LoginRequest;
import com.mp.projects.lovable_clone.dto.auth.SignUpRequest;
import org.jspecify.annotations.Nullable;

public interface AuthService {
     AuthResponse signUp(SignUpRequest request);

     AuthResponse login(LoginRequest request);
}
