package com.mp.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @Email @NotBlank String username,
        @Size(min = 1, max = 35) String name,
        @Size(min = 4, max = 50) String password
) {
}
