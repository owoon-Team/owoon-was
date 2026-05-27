package com.owoon.was.domain.user.service;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import com.owoon.was.security.jwt.JwtUtil;
import com.owoon.was.domain.user.dto.request.LoginRequest;
import com.owoon.was.domain.user.dto.request.UserCreateRequest;
import com.owoon.was.domain.user.dto.response.AuthResponse;
import com.owoon.was.domain.user.entity.User;
import com.owoon.was.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입 후 access token을 발급한다.
     */
    @Transactional
    public AuthResponse signup(UserCreateRequest request) {
        checkEmailDuplication(request.email());

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .build();

        User savedUser = userRepository.save(user);
        String accessToken = jwtUtil.createAccessToken(savedUser.getId(), savedUser.getEmail());

        return AuthResponse.from(savedUser, accessToken);
    }

    /**
     * 로그인 정보를 검증하고 access token을 발급한다.
     */
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail());

        return AuthResponse.from(user, accessToken);
    }

    /**
     * 이메일 중복 여부를 확인한다.
     */
    public void checkEmailDuplication(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
