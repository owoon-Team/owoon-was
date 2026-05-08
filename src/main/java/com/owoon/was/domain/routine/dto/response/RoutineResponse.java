package com.owoon.was.domain.routine.dto.response;

import com.owoon.was.domain.routine.entity.Routine;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public record RoutineResponse(
        Long id,
        Long userId,
        String name,
        String description,
        LocalDateTime createdAt,
        List<RoutineExerciseResponse> exercises
) {

    public static RoutineResponse from(Routine routine) {
        List<RoutineExerciseResponse> exercises = routine.getRoutineExercises().stream()
                .sorted(Comparator.comparing(routineExercise -> routineExercise.getExerciseOrder()))
                .map(RoutineExerciseResponse::from)
                .toList();

        return new RoutineResponse(
                routine.getId(),
                routine.getUser().getId(),
                routine.getName(),
                routine.getDescription(),
                routine.getCreatedAt(),
                exercises
        );
    }
}
