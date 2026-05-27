package com.owoon.was.domain.user.dto.response;

import com.owoon.was.common.enums.dto.response.EnumResponse;
import com.owoon.was.domain.user.entity.UserProfile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserProfileResponse(
        Long id,
        EnumResponse exerciseLevel,
        LocalDate birthDate,
        BigDecimal heightCm,
        BigDecimal weightKg,
        EnumResponse gender,
        EnumResponse mainGoal,
        LocalDateTime createdAt,
        Long userId
) {

    public static UserProfileResponse from(UserProfile userProfile) {
        return new UserProfileResponse(
                userProfile.getId(),
                EnumResponse.from(userProfile.getExerciseLevel()),
                userProfile.getBirthDate(),
                userProfile.getHeightCm(),
                userProfile.getWeightKg(),
                EnumResponse.from(userProfile.getGender()),
                EnumResponse.from(userProfile.getMainGoal()),
                userProfile.getCreatedAt(),
                userProfile.getUser().getId()
        );
    }
}
