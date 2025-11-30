package com.mp.projects.lovable_clone.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
