package com.owoon.was.domain.user.dto.request;

import com.owoon.was.domain.user.entity.enums.ExerciseLevel;
import com.owoon.was.domain.user.entity.enums.Gender;
import com.owoon.was.domain.user.entity.enums.MainGoal;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserProfileCreateRequest(
        @NotNull(message = "운동 수준은 필수 입력입니다.")
        ExerciseLevel exerciseLevel,

        @NotNull(message = "생년월일은 필수 입력입니다.")
        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        LocalDate birthDate,

        @NotNull(message = "키는 필수 입력입니다.")
        @DecimalMin(value = "0.01", message = "키는 0보다 커야 합니다.")
        @DecimalMax(value = "999.99", message = "키는 999.99 이하로 입력해야 합니다.")
        BigDecimal heightCm,

        @NotNull(message = "몸무게는 필수 입력입니다.")
        @DecimalMin(value = "0.01", message = "몸무게는 0보다 커야 합니다.")
        @DecimalMax(value = "999.99", message = "몸무게는 999.99 이하로 입력해야 합니다.")
        BigDecimal weightKg,

        @NotNull(message = "성별은 필수 입력입니다.")
        Gender gender,

        @NotNull(message = "주요 목표는 필수 입력입니다.")
        MainGoal mainGoal
) {
}
