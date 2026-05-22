package com.owoon.was.domain.routineexerciseresult.controller;

import com.owoon.was.domain.routineexerciseresult.controller.api.RoutineExerciseResultApi;
import com.owoon.was.domain.routineexerciseresult.dto.response.RoutineExerciseResultResponse;
import com.owoon.was.domain.routineexerciseresult.service.RoutineExerciseResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/routine-sessions/{sessionId}/exercise-results")
public class RoutineExerciseResultController implements RoutineExerciseResultApi {

    private final RoutineExerciseResultService routineExerciseResultService;

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineExerciseResultResponse>> getRoutineExerciseResults(
            @PathVariable Long userId,
            @PathVariable Long sessionId
    ) {
        return ResponseEntity.ok(routineExerciseResultService.getRoutineExerciseResults(userId, sessionId));
    }

    @Override
    @GetMapping("/{resultId}")
    public ResponseEntity<RoutineExerciseResultResponse> getRoutineExerciseResult(
            @PathVariable Long userId,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        return ResponseEntity.ok(routineExerciseResultService.getRoutineExerciseResult(userId, sessionId, resultId));
    }
}
