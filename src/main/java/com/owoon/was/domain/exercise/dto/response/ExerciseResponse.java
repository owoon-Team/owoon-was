package com.owoon.was.domain.exercise.dto.response;

import com.owoon.was.domain.exercise.entity.Exercise;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCategory;

public record ExerciseResponse(
        Long id,
        String code,
        String name,
        ExerciseCategory category,
        String categoryDescription
) {

    public static ExerciseResponse from(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getCode().name(),
                exercise.getName(),
                exercise.getCategory(),
                exercise.getCategory().getDescription()
        );
    }
}
