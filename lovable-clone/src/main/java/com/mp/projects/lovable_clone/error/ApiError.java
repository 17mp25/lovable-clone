package com.mp.projects.lovable_clone.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiError(
        HttpStatus status,
        String message,
        Instant timestamp,
        @JsonInclude(JsonInclude.Include.NON_NULL) List<ApiFieldError> apiFieldErrors
) {
    public ApiError(HttpStatus status, String message) {
        this(status, message, Instant.now(), null);
    }

    public ApiError(HttpStatus status, String message, List<ApiFieldError> apiFieldErrors) {
        this(status, message, Instant.now(), apiFieldErrors);
    }
}

record ApiFieldError(
        String field,
        String message
) {
}
