package com.mp.projects.lovable_clone.service;

import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import org.jspecify.annotations.Nullable;

public interface UserService {

    @Nullable UserProfileResponse getProfile();
}
