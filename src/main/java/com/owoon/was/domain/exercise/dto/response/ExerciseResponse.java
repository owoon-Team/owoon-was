package com.owoon.was.domain.exercise.dto.response;

import com.owoon.was.domain.exercise.entity.Exercise;

public record ExerciseResponse(
        Long id,
        String code,
        String name,
        String description
) {

    public static ExerciseResponse from(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getCode().name(),
                exercise.getName(),
                exercise.getDescription()
        );
    }
}
