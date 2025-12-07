package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.mp.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.mp.projects.lovable_clone.dto.subscription.PortalResponse;
import com.mp.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import com.mp.projects.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getMySubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
