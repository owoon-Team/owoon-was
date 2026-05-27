package com.owoon.was.common.enums.dto.response;

import java.util.List;

public record EnumGroupResponse(
        List<EnumResponse> exerciseLevels,
        List<EnumResponse> genders,
        List<EnumResponse> mainGoals,
        List<EnumResponse> exerciseCodes,
        List<EnumResponse> exerciseCategories
) {
}
