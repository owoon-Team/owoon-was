package com.owoon.was.domain.postureerrorlog.dto.response;

import com.owoon.was.domain.postureerrorlog.entity.PostureErrorLog;

import java.time.LocalDateTime;

public record PostureErrorLogResponse(
        Long id,
        Long routineExerciseResultId,
        Integer setNumber,
        Integer repNumber,
        String errorType,
        String errorMessage,
        LocalDateTime createdAt
) {

    public static PostureErrorLogResponse from(PostureErrorLog postureErrorLog) {
        return new PostureErrorLogResponse(
                postureErrorLog.getId(),
                postureErrorLog.getRoutineExerciseResult().getId(),
                postureErrorLog.getSetNumber(),
                postureErrorLog.getRepNumber(),
                postureErrorLog.getErrorType(),
                postureErrorLog.getErrorMessage(),
                postureErrorLog.getCreatedAt()
        );
    }
}
