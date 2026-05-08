package com.owoon.was.domain.routine.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RoutineCreateRequest(
        @NotBlank(message = "루틴 이름은 필수 입력입니다.")
        @Size(max = 100, message = "루틴 이름은 100자 이하로 입력해야 합니다.")
        String name,

        String description,

        @Valid
        @NotEmpty(message = "루틴에는 운동이 1개 이상 포함되어야 합니다.")
        List<RoutineExerciseCreateRequest> exercises
) {
}
