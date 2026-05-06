package com.owoon.was.domain.exercise.service;

import com.owoon.was.domain.exercise.entity.Exercise;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCode;
import com.owoon.was.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ExerciseDataInitializer implements ApplicationRunner {

    private final ExerciseRepository exerciseRepository;

    @Override
    public void run(ApplicationArguments args) {
        Arrays.stream(ExerciseCode.values())
                .filter(code -> !exerciseRepository.existsByCode(code))
                .map(this::createDefaultExercise)
                .forEach(exerciseRepository::save);
    }

    private Exercise createDefaultExercise(ExerciseCode code) {
        return Exercise.builder()
                .code(code)
                .name(code.getDescription())
                .description(getDefaultDescription(code))
                .build();
    }

    private String getDefaultDescription(ExerciseCode code) {
        return switch (code) {
            case PUSH_UP -> "상체운동";
            case SIT_UP -> "복근운동";
            case SQUAT, LUNGE -> "하체운동";
            case HIP_THRUST -> "둔근운동";
        };
    }
}
