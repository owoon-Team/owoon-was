package com.owoon.was.domain.routine.controller;

import com.owoon.was.common.security.jwt.JwtUtil;
import com.owoon.was.domain.routine.controller.api.MyRoutineApi;
import com.owoon.was.domain.routine.dto.response.RoutineResponse;
import com.owoon.was.domain.routine.dto.response.RoutineSummaryResponse;
import com.owoon.was.domain.routine.service.RoutineService;
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
@RequestMapping("/api/v1/routines/me")
public class MyRoutineController implements MyRoutineApi {

    private final RoutineService routineService;
    private final JwtUtil jwtUtil;

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineSummaryResponse>> getMyRoutines(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.ok(routineService.getRoutines(userId));
    }

    @Override
    @GetMapping("/{routineId}")
    public ResponseEntity<RoutineResponse> getMyRoutine(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long routineId
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.ok(routineService.getRoutine(userId, routineId));
    }
}
