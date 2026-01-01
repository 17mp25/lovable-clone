package com.mp.projects.lovable_clone.service.impl;

import com.mp.projects.lovable_clone.dto.auth.AuthResponse;
import com.mp.projects.lovable_clone.dto.auth.LoginRequest;
import com.mp.projects.lovable_clone.dto.auth.SignUpRequest;
import com.mp.projects.lovable_clone.entity.User;
import com.mp.projects.lovable_clone.error.BadRequestException;
import com.mp.projects.lovable_clone.mapper.UserMapper;
import com.mp.projects.lovable_clone.repository.UserRepository;
import com.mp.projects.lovable_clone.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)

public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signUp(SignUpRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException(
                    "Username already exists with value: " + request.username());
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        
        return new AuthResponse("Dummy",
                userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
