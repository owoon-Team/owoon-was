package com.owoon.was.common.enums.service;

import com.owoon.was.common.enums.dto.response.EnumGroupResponse;
import com.owoon.was.common.enums.dto.response.EnumResponse;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCategory;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCode;
import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSeverity;
import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSource;
import com.owoon.was.domain.user.entity.enums.ExerciseLevel;
import com.owoon.was.domain.user.entity.enums.Gender;
import com.owoon.was.domain.user.entity.enums.MainGoal;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnumService {

    /**
     * 화면 선택 옵션으로 사용하는 Enum 목록을 조회한다.
     */
    public EnumGroupResponse getEnums() {
        return new EnumGroupResponse(
                toResponses(ExerciseLevel.values()),
                toResponses(Gender.values()),
                toResponses(MainGoal.values()),
                toResponses(ExerciseCode.values()),
                toResponses(ExerciseCategory.values()),
                toResponses(FeedbackSeverity.values()),
                toResponses(FeedbackSource.values())
        );
    }

    private List<EnumResponse> toResponses(ExerciseLevel[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }

    private List<EnumResponse> toResponses(Gender[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }

    private List<EnumResponse> toResponses(MainGoal[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }

    private List<EnumResponse> toResponses(ExerciseCode[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }

    private List<EnumResponse> toResponses(ExerciseCategory[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }

    private List<EnumResponse> toResponses(FeedbackSeverity[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }

    private List<EnumResponse> toResponses(FeedbackSource[] values) {
        return Arrays.stream(values)
                .map(EnumResponse::from)
                .toList();
    }
}
