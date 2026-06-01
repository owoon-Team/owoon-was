package com.owoon.was.domain.routineexerciseresult.dto.request;

import com.owoon.was.domain.feedbacklog.dto.request.FeedbackLogCreateRequest;
import com.owoon.was.domain.postureerrorlog.dto.request.PostureErrorLogCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record RoutineExerciseResultCreateRequest(
        @NotNull(message = "루틴 운동 ID는 필수 입력입니다.")
        Long routineExerciseId,

        @NotNull(message = "수행 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "수행 반복 횟수는 0 이상이어야 합니다.")
        Integer completedReps,

        @NotNull(message = "수행 세트 수는 필수 입력입니다.")
        @Min(value = 0, message = "수행 세트 수는 0 이상이어야 합니다.")
        Integer completedSets,

        @NotNull(message = "정상 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "정상 반복 횟수는 0 이상이어야 합니다.")
        Integer normalReps,

        @NotNull(message = "오류 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "오류 반복 횟수는 0 이상이어야 합니다.")
        Integer errorReps,

        @NotNull(message = "정확도 점수는 필수 입력입니다.")
        @DecimalMin(value = "0.00", message = "정확도 점수는 0 이상이어야 합니다.")
        @DecimalMax(value = "100.00", message = "정확도 점수는 100 이하이어야 합니다.")
        BigDecimal accuracyScore,

        @NotNull(message = "운동 시간은 필수 입력입니다.")
        @Min(value = 0, message = "운동 시간은 0초 이상이어야 합니다.")
        Integer durationSeconds,

        @NotNull(message = "운동 시작 시간은 필수 입력입니다.")
        LocalDateTime startedAt,

        @NotNull(message = "운동 종료 시간은 필수 입력입니다.")
        LocalDateTime endedAt,

        @Valid
        List<PostureErrorLogCreateRequest> postureErrorLogs,

        @Valid
        List<FeedbackLogCreateRequest> feedbackLogs
) {
}
