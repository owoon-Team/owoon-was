package com.owoon.was.domain.user.dto.response;

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
}
