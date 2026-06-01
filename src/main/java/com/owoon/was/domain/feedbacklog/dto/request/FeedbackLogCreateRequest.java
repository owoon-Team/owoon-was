package com.owoon.was.domain.feedbacklog.dto.request;

import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSeverity;
import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackLogCreateRequest(
        @Min(value = 1, message = "세트 번호는 1 이상이어야 합니다.")
        Integer setNumber,

        @Min(value = 1, message = "반복 번호는 1 이상이어야 합니다.")
        Integer repNumber,

        @NotBlank(message = "피드백 유형은 필수 입력입니다.")
        @Size(max = 100, message = "피드백 유형은 100자를 초과할 수 없습니다.")
        String feedbackType,

        @NotBlank(message = "피드백 메시지는 필수 입력입니다.")
        @Size(max = 500, message = "피드백 메시지는 500자를 초과할 수 없습니다.")
        String feedbackMessage,

        @NotNull(message = "피드백 심각도는 필수 입력입니다.")
        FeedbackSeverity severity,

        @NotNull(message = "피드백 출처는 필수 입력입니다.")
        FeedbackSource feedbackSource
) {
}
