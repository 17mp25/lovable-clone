package com.mp.projects.lovable_clone.mapper;

import com.mp.projects.lovable_clone.dto.auth.SignUpRequest;
import com.mp.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.mp.projects.lovable_clone.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-01T12:25:23+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(SignUpRequest signUpRequest) {
        if ( signUpRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( signUpRequest.username() );
        user.password( signUpRequest.password() );
        user.name( signUpRequest.name() );

        return user.build();
    }

    @Override
    public UserProfileResponse toUserProfileResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String name = null;

        id = user.getId();
        username = user.getUsername();
        name = user.getName();

        UserProfileResponse userProfileResponse = new UserProfileResponse( id, username, name );

        return userProfileResponse;
    }
}
