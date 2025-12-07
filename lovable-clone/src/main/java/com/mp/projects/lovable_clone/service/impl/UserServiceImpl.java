package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.mp.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.mp.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import com.mp.projects.lovable_clone.service.UsageService;
import com.mp.projects.lovable_clone.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserProfileResponse getProfile() {
        return null;
    }
}
