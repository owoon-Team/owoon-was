package com.owoon.was.domain.routineexerciseresult.controller;

import com.owoon.was.common.security.jwt.JwtUtil;
import com.owoon.was.domain.routineexerciseresult.controller.api.MyRoutineExerciseResultApi;
import com.owoon.was.domain.routineexerciseresult.dto.response.RoutineExerciseResultResponse;
import com.owoon.was.domain.routineexerciseresult.service.RoutineExerciseResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routine-sessions/me/{sessionId}/exercise-results")
public class MyRoutineExerciseResultController implements MyRoutineExerciseResultApi {

    private final RoutineExerciseResultService routineExerciseResultService;
    private final JwtUtil jwtUtil;

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineExerciseResultResponse>> getMyRoutineExerciseResults(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long sessionId
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.ok(routineExerciseResultService.getRoutineExerciseResults(userId, sessionId));
    }

    @Override
    @GetMapping("/{resultId}")
    public ResponseEntity<RoutineExerciseResultResponse> getMyRoutineExerciseResult(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.ok(routineExerciseResultService.getRoutineExerciseResult(userId, sessionId, resultId));
    }
}
