package com.owoon.was.domain.feedbacklog.dto.response;

import com.owoon.was.domain.feedbacklog.entity.FeedbackLog;
import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSeverity;
import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSource;

import java.time.LocalDateTime;

public record FeedbackLogResponse(
        Long id,
        Long routineExerciseResultId,
        Integer setNumber,
        Integer repNumber,
        String feedbackType,
        String feedbackMessage,
        FeedbackSeverity severity,
        FeedbackSource feedbackSource,
        LocalDateTime createdAt
) {

    public static FeedbackLogResponse from(FeedbackLog feedbackLog) {
        return new FeedbackLogResponse(
                feedbackLog.getId(),
                feedbackLog.getRoutineExerciseResult().getId(),
                feedbackLog.getSetNumber(),
                feedbackLog.getRepNumber(),
                feedbackLog.getFeedbackType(),
                feedbackLog.getFeedbackMessage(),
                feedbackLog.getSeverity(),
                feedbackLog.getFeedbackSource(),
                feedbackLog.getCreatedAt()
        );
    }
}
