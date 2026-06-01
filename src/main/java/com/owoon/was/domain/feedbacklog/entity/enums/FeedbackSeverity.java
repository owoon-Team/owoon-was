package com.owoon.was.domain.feedbacklog.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackSeverity {
    SUCCESS("성공"),
    INFO("정보"),
    WARNING("주의"),
    ERROR("오류");

    private final String description;
}
