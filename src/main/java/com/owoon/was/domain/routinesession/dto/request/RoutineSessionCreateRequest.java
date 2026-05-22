package com.owoon.was.domain.routinesession.dto.request;

import com.owoon.was.domain.routineexerciseresult.dto.request.RoutineExerciseResultCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record RoutineSessionCreateRequest(
        @NotNull(message = "루틴 ID는 필수 입력입니다.")
        Long routineId,

        @NotNull(message = "전체 목표 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "전체 목표 반복 횟수는 0 이상이어야 합니다.")
        Integer totalTargetReps,

        @NotNull(message = "전체 수행 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "전체 수행 반복 횟수는 0 이상이어야 합니다.")
        Integer totalCompletedReps,

        @NotNull(message = "전체 정상 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "전체 정상 반복 횟수는 0 이상이어야 합니다.")
        Integer totalNormalReps,

        @NotNull(message = "전체 오류 반복 횟수는 필수 입력입니다.")
        @Min(value = 0, message = "전체 오류 반복 횟수는 0 이상이어야 합니다.")
        Integer totalErrorReps,

        @NotNull(message = "평균 정확도 점수는 필수 입력입니다.")
        @DecimalMin(value = "0.00", message = "평균 정확도 점수는 0 이상이어야 합니다.")
        @DecimalMax(value = "100.00", message = "평균 정확도 점수는 100 이하이어야 합니다.")
        BigDecimal averageAccuracyScore,

        @NotNull(message = "전체 운동 시간은 필수 입력입니다.")
        @Min(value = 0, message = "전체 운동 시간은 0초 이상이어야 합니다.")
        Integer totalDurationSeconds,

        @NotNull(message = "운동 시작 시간은 필수 입력입니다.")
        LocalDateTime startedAt,

        @NotNull(message = "운동 종료 시간은 필수 입력입니다.")
        LocalDateTime endedAt,

        @Valid
        @NotEmpty(message = "루틴 실행 기록에는 운동별 결과가 1개 이상 포함되어야 합니다.")
        List<RoutineExerciseResultCreateRequest> exerciseResults
) {
}
