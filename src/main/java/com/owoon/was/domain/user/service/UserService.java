package com.owoon.was.domain.user.service;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import com.owoon.was.domain.user.dto.request.UserProfileCreateRequest;
import com.owoon.was.domain.user.dto.response.UserProfileResponse;
import com.owoon.was.domain.user.dto.response.UserResponse;
import com.owoon.was.domain.user.entity.User;
import com.owoon.was.domain.user.entity.UserProfile;
import com.owoon.was.domain.user.repository.UserProfileRepository;
import com.owoon.was.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    /**
     * 회원 ID로 기본 정보를 조회한다.
     */
    public UserResponse getUser(Long userId) {
        User user = findUser(userId);
        return UserResponse.from(user);
    }

    /**
     * 회원의 신체 정보를 등록한다.
     */
    @Transactional
    public UserProfileResponse createUserProfile(Long userId, UserProfileCreateRequest request) {
        User user = findUser(userId);
        validateDuplicateUserProfile(userId);

        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .exerciseLevel(request.exerciseLevel())
                .birthDate(request.birthDate())
                .heightCm(request.heightCm())
                .weightKg(request.weightKg())
                .gender(request.gender())
                .mainGoal(request.mainGoal())
                .build();

        return UserProfileResponse.from(userProfileRepository.save(userProfile));
    }

    /**
     * 회원 ID로 신체 정보를 조회한다.
     */
    public UserProfileResponse getUserProfile(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_PROFILE_NOT_FOUND));

        return UserProfileResponse.from(userProfile);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private void validateDuplicateUserProfile(Long userId) {
        if (userProfileRepository.existsByUserId(userId)) {
            throw new CustomException(ErrorCode.DUPLICATE_USER_PROFILE);
        }
    }
}
