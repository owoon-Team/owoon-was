package com.owoon.was.domain.routine.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RoutineExerciseCreateRequest(
        @NotNull(message = "운동 ID는 필수 입력입니다.")
        Long exerciseId,

        @NotNull(message = "루틴 내 운동 순서는 필수 입력입니다.")
        @Min(value = 1, message = "루틴 내 운동 순서는 1 이상이어야 합니다.")
        Integer exerciseOrder,

        @NotNull(message = "목표 반복 횟수는 필수 입력입니다.")
        @Min(value = 1, message = "목표 반복 횟수는 1 이상이어야 합니다.")
        Integer targetReps,

        @NotNull(message = "목표 세트 수는 필수 입력입니다.")
        @Min(value = 1, message = "목표 세트 수는 1 이상이어야 합니다.")
        Integer targetSets,

        @Min(value = 0, message = "운동 후 휴식 시간은 0초 이상이어야 합니다.")
        Integer restSeconds
) {
}
