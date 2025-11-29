package com.mp.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {
    Long id;
    String name;
    String stripeProceId;
    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews;//max number of previews per day
    Boolean unlimitedAi;//Unlimited access to LLM, Ignore maxTokenPerDay if true
    Boolean active;
}
