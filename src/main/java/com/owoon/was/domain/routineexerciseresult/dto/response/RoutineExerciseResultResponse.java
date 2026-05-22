package com.owoon.was.domain.routineexerciseresult.dto.response;

import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RoutineExerciseResultResponse(
        Long id,
        Long routineExerciseId,
        Long exerciseId,
        String exerciseName,
        Integer exerciseOrderSnapshot,
        Integer targetRepsSnapshot,
        Integer targetSetsSnapshot,
        Integer restSecondsSnapshot,
        Integer completedReps,
        Integer completedSets,
        Integer normalReps,
        Integer errorReps,
        BigDecimal accuracyScore,
        Integer durationSeconds,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        LocalDateTime createdAt
) {

    public static RoutineExerciseResultResponse from(RoutineExerciseResult routineExerciseResult) {
        return new RoutineExerciseResultResponse(
                routineExerciseResult.getId(),
                routineExerciseResult.getRoutineExercise().getId(),
                routineExerciseResult.getRoutineExercise().getExercise().getId(),
                routineExerciseResult.getRoutineExercise().getExercise().getName(),
                routineExerciseResult.getExerciseOrderSnapshot(),
                routineExerciseResult.getTargetRepsSnapshot(),
                routineExerciseResult.getTargetSetsSnapshot(),
                routineExerciseResult.getRestSecondsSnapshot(),
                routineExerciseResult.getCompletedReps(),
                routineExerciseResult.getCompletedSets(),
                routineExerciseResult.getNormalReps(),
                routineExerciseResult.getErrorReps(),
                routineExerciseResult.getAccuracyScore(),
                routineExerciseResult.getDurationSeconds(),
                routineExerciseResult.getStartedAt(),
                routineExerciseResult.getEndedAt(),
                routineExerciseResult.getCreatedAt()
        );
    }
}
