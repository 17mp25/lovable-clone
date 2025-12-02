package com.mp.projects.lovable_clone.service;

import com.mp.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.mp.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.mp.projects.lovable_clone.dto.subscription.PortalResponse;
import com.mp.projects.lovable_clone.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse getMySubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
