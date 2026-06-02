package com.owoon.was.domain.routine.controller;

import com.owoon.was.domain.routine.controller.api.MyRoutineApi;
import com.owoon.was.domain.routine.dto.response.RoutineResponse;
import com.owoon.was.domain.routine.dto.response.RoutineSummaryResponse;
import com.owoon.was.domain.routine.service.RoutineService;
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
@RequestMapping("/api/v1/routines/me")
public class MyRoutineController implements MyRoutineApi {

    private final RoutineService routineService;

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineSummaryResponse>> getMyRoutines(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(routineService.getRoutines(userDetails.getUserId()));
    }

    @Override
    @GetMapping("/{routineId}")
    public ResponseEntity<RoutineResponse> getMyRoutine(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long routineId
    ) {
        return ResponseEntity.ok(routineService.getRoutine(userDetails.getUserId(), routineId));
    }
}
