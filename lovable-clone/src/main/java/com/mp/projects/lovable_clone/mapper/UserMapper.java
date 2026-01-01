package com.mp.projects.lovable_clone.mapper;

import com.mp.projects.lovable_clone.dto.auth.SignUpRequest;
import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.mp.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignUpRequest signUpRequest);

    UserProfileResponse toUserProfileResponse(User user);
}
