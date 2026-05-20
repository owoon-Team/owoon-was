package com.owoon.was.domain.routinesession.dto.response;

import com.owoon.was.domain.routinesession.entity.RoutineSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        LocalDateTime createdAt
) {

    public static RoutineSessionResponse from(RoutineSession routineSession) {
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
                routineSession.getStartedAt(),
                routineSession.getEndedAt(),
                routineSession.getCreatedAt()
        );
    }
}
