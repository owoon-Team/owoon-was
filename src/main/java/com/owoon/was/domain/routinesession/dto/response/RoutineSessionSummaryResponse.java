package com.owoon.was.domain.routinesession.dto.response;

import com.owoon.was.domain.routinesession.entity.RoutineSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RoutineSessionSummaryResponse(
        Long id,
        Long userId,
        Long routineId,
        String routineName,
        Integer totalCompletedReps,
        Integer totalNormalReps,
        Integer totalErrorReps,
        BigDecimal averageAccuracyScore,
        Integer totalDurationSeconds,
        BigDecimal totalCaloriesBurned,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {

    public static RoutineSessionSummaryResponse from(RoutineSession routineSession) {
        return new RoutineSessionSummaryResponse(
                routineSession.getId(),
                routineSession.getUser().getId(),
                routineSession.getRoutine().getId(),
                routineSession.getRoutine().getName(),
                routineSession.getTotalCompletedReps(),
                routineSession.getTotalNormalReps(),
                routineSession.getTotalErrorReps(),
                routineSession.getAverageAccuracyScore(),
                routineSession.getTotalDurationSeconds(),
                routineSession.getTotalCaloriesBurned(),
                routineSession.getStartedAt(),
                routineSession.getEndedAt()
        );
    }
}
