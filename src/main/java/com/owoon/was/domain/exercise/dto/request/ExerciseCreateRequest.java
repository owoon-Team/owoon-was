package com.owoon.was.domain.exercise.dto.request;

import com.owoon.was.domain.exercise.entity.enums.ExerciseCategory;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExerciseCreateRequest(
        @NotNull(message = "운동 코드는 필수 입력입니다.")
        ExerciseCode code,

        @NotBlank(message = "운동명은 필수 입력입니다.")
        @Size(max = 100, message = "운동명은 100자 이하로 입력해야 합니다.")
        String name,

        @NotNull(message = "운동 카테고리는 필수 입력입니다.")
        ExerciseCategory category
) {
}
