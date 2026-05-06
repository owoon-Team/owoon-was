package com.owoon.was.domain.exercise.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseCategory {
    UPPER_BODY("상체운동"),
    CORE("복근운동"),
    LOWER_BODY("하체운동"),
    GLUTES("둔근운동");

    private final String description;
}
