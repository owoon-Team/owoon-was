package com.owoon.was.domain.routine.dto.response;

import com.owoon.was.domain.exercise.dto.response.ExerciseResponse;
import com.owoon.was.domain.routine.entity.RoutineExercise;

public record RoutineExerciseResponse(
        Long id,
        Integer exerciseOrder,
        Integer targetReps,
        Integer targetSets,
        Integer restSeconds,
        ExerciseResponse exercise
) {

    public static RoutineExerciseResponse from(RoutineExercise routineExercise) {
        return new RoutineExerciseResponse(
                routineExercise.getId(),
                routineExercise.getExerciseOrder(),
                routineExercise.getTargetReps(),
                routineExercise.getTargetSets(),
                routineExercise.getRestSeconds(),
                ExerciseResponse.from(routineExercise.getExercise())
        );
    }
}
