package com.mp.projects.lovable_clone.service;

import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserProfileResponse getProfile();
}
