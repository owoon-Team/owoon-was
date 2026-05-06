package com.owoon.was.domain.user.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainGoal {
    DIET("다이어트"),
    CONSISTENT_EXERCISE("꾸준히 운동하기"),
    DEFINED_ABS("선명한 복근 만들기"),
    SLIM_LOWER_BODY("슬림한 하체 라인 만들기"),
    MUSCLE_MASS_GAIN("전체적인 근육량 증가"),
    BULK_UP("벌크업");

    private final String description;
}
