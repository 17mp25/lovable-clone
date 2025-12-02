package com.mp.projects.lovable_clone.service;

import com.mp.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.mp.projects.lovable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
