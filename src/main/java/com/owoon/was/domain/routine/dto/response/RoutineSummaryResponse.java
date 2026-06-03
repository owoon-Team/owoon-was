package com.owoon.was.domain.routine.dto.response;

import com.owoon.was.domain.routine.entity.Routine;

import java.time.LocalDateTime;

public record RoutineSummaryResponse(
        Long id,
        Long userId,
        String name,
        String description,
        Integer exerciseCount,
        LocalDateTime createdAt
) {

    public static RoutineSummaryResponse from(Routine routine) {
        int activeExerciseCount = (int) routine.getRoutineExercises().stream()
                .filter(routineExercise -> !routineExercise.isDeleted())
                .count();

        return new RoutineSummaryResponse(
                routine.getId(),
                routine.getUser().getId(),
                routine.getName(),
                routine.getDescription(),
                activeExerciseCount,
                routine.getCreatedAt()
        );
    }
}
