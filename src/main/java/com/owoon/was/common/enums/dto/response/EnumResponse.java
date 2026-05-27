package com.owoon.was.common.enums.dto.response;

import com.owoon.was.domain.exercise.entity.enums.ExerciseCategory;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCode;
import com.owoon.was.domain.user.entity.enums.ExerciseLevel;
import com.owoon.was.domain.user.entity.enums.Gender;
import com.owoon.was.domain.user.entity.enums.MainGoal;

public record EnumResponse(
        String code,
        String description
) {

    public static EnumResponse from(ExerciseLevel exerciseLevel) {
        return new EnumResponse(exerciseLevel.name(), exerciseLevel.getDescription());
    }

    public static EnumResponse from(Gender gender) {
        return new EnumResponse(gender.name(), gender.getDescription());
    }

    public static EnumResponse from(MainGoal mainGoal) {
        return new EnumResponse(mainGoal.name(), mainGoal.getDescription());
    }

    public static EnumResponse from(ExerciseCode exerciseCode) {
        return new EnumResponse(exerciseCode.name(), exerciseCode.getDescription());
    }

    public static EnumResponse from(ExerciseCategory exerciseCategory) {
        return new EnumResponse(exerciseCategory.name(), exerciseCategory.getDescription());
    }
}
