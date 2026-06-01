package com.owoon.was.domain.postureerrorlog.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostureErrorLogCreateRequest(
        @NotNull(message = "세트 번호는 필수 입력입니다.")
        @Min(value = 1, message = "세트 번호는 1 이상이어야 합니다.")
        Integer setNumber,

        @NotNull(message = "반복 번호는 필수 입력입니다.")
        @Min(value = 1, message = "반복 번호는 1 이상이어야 합니다.")
        Integer repNumber,

        @NotBlank(message = "자세 오류 유형은 필수 입력입니다.")
        @Size(max = 100, message = "자세 오류 유형은 100자를 초과할 수 없습니다.")
        String errorType,

        @NotBlank(message = "자세 오류 메시지는 필수 입력입니다.")
        @Size(max = 500, message = "자세 오류 메시지는 500자를 초과할 수 없습니다.")
        String errorMessage
) {
}
