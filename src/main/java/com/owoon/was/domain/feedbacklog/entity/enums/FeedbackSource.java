package com.owoon.was.domain.feedbacklog.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackSource {
    REALTIME_ANALYSIS("실시간 자세 분석"),
    FINAL_SUMMARY("최종 운동 요약"),
    SYSTEM_GUIDE("시스템 가이드");

    private final String description;
}
