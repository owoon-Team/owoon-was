package com.owoon.was.domain.user.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseLevel {
    BEGINNER("입문"),
    INTERMEDIATE("중급"),
    ADVANCED("전문가");

    private final String description;
}
