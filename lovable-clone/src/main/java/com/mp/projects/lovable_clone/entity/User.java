package com.mp.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String email;
    String passwordHash;
    String name;
    String avatarUrl;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;// soft delete

}
