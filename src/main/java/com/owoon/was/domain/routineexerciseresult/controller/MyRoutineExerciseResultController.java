package com.owoon.was.domain.routineexerciseresult.controller;

import com.owoon.was.domain.routineexerciseresult.controller.api.MyRoutineExerciseResultApi;
import com.owoon.was.domain.routineexerciseresult.dto.response.RoutineExerciseResultResponse;
import com.owoon.was.domain.routineexerciseresult.service.RoutineExerciseResultService;
import com.owoon.was.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routine-sessions/me/{sessionId}/exercise-results")
public class MyRoutineExerciseResultController implements MyRoutineExerciseResultApi {

    private final RoutineExerciseResultService routineExerciseResultService;

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineExerciseResultResponse>> getMyRoutineExerciseResults(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long sessionId
    ) {
        return ResponseEntity.ok(routineExerciseResultService.getRoutineExerciseResults(userDetails.getUserId(), sessionId));
    }

    @Override
    @GetMapping("/{resultId}")
    public ResponseEntity<RoutineExerciseResultResponse> getMyRoutineExerciseResult(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        return ResponseEntity.ok(routineExerciseResultService.getRoutineExerciseResult(userDetails.getUserId(), sessionId, resultId));
    }
}
