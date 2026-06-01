package com.owoon.was.domain.routineexerciseresult.dto.response;

import com.owoon.was.domain.feedbacklog.dto.response.FeedbackLogResponse;
import com.owoon.was.domain.feedbacklog.entity.FeedbackLog;
import com.owoon.was.domain.postureerrorlog.dto.response.PostureErrorLogResponse;
import com.owoon.was.domain.postureerrorlog.entity.PostureErrorLog;
import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
        LocalDateTime createdAt,
        List<PostureErrorLogResponse> postureErrorLogs,
        List<FeedbackLogResponse> feedbackLogs
) {

    public static RoutineExerciseResultResponse from(RoutineExerciseResult routineExerciseResult) {
        List<PostureErrorLogResponse> postureErrorLogs = routineExerciseResult.getPostureErrorLogs().stream()
                .sorted(Comparator
                        .comparing(PostureErrorLog::getSetNumber)
                        .thenComparing(PostureErrorLog::getRepNumber))
                .map(PostureErrorLogResponse::from)
                .toList();

        List<FeedbackLogResponse> feedbackLogs = routineExerciseResult.getFeedbackLogs().stream()
                .sorted(Comparator
                        .comparing(FeedbackLog::getCreatedAt)
                        .thenComparing(FeedbackLog::getId, Comparator.nullsLast(Long::compareTo)))
                .map(FeedbackLogResponse::from)
                .toList();

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
                routineExerciseResult.getCreatedAt(),
                postureErrorLogs,
                feedbackLogs
        );
    }
}
