package com.owoon.was.domain.user.dto.request;

import com.owoon.was.domain.user.entity.enums.ExerciseLevel;
import com.owoon.was.domain.user.entity.enums.Gender;
import com.owoon.was.domain.user.entity.enums.MainGoal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserProfileCreateRequest(
        ExerciseLevel exerciseLevel,
        LocalDate birthDate,
        BigDecimal heightCm,
        BigDecimal weightKg,
        Gender gender,
        MainGoal mainGoal
) {
}
