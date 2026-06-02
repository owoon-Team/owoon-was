package com.owoon.was.domain.routinesession.dto.response;

import com.owoon.was.domain.routineexerciseresult.dto.response.RoutineExerciseResultResponse;
import com.owoon.was.domain.routinesession.entity.RoutineSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public record RoutineSessionResponse(
        Long id,
        Long userId,
        Long routineId,
        String routineName,
        Integer totalTargetReps,
        Integer totalCompletedReps,
        Integer totalNormalReps,
        Integer totalErrorReps,
        BigDecimal averageAccuracyScore,
        Integer totalDurationSeconds,
        BigDecimal totalCaloriesBurned,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        LocalDateTime createdAt,
        List<RoutineExerciseResultResponse> exerciseResults
) {

    public static RoutineSessionResponse from(RoutineSession routineSession) {
        List<RoutineExerciseResultResponse> exerciseResults = routineSession.getRoutineExerciseResults().stream()
                .sorted(Comparator.comparing(routineExerciseResult -> routineExerciseResult.getExerciseOrderSnapshot()))
                .map(RoutineExerciseResultResponse::from)
                .toList();

        return new RoutineSessionResponse(
                routineSession.getId(),
                routineSession.getUser().getId(),
                routineSession.getRoutine().getId(),
                routineSession.getRoutine().getName(),
                routineSession.getTotalTargetReps(),
                routineSession.getTotalCompletedReps(),
                routineSession.getTotalNormalReps(),
                routineSession.getTotalErrorReps(),
                routineSession.getAverageAccuracyScore(),
                routineSession.getTotalDurationSeconds(),
                routineSession.getTotalCaloriesBurned(),
                routineSession.getStartedAt(),
                routineSession.getEndedAt(),
                routineSession.getCreatedAt(),
                exerciseResults
        );
    }
}
