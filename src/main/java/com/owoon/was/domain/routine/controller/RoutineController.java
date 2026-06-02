package com.owoon.was.domain.routine.controller;

import com.owoon.was.domain.routine.controller.api.RoutineApi;
import com.owoon.was.domain.routine.dto.request.RoutineCreateRequest;
import com.owoon.was.domain.routine.dto.response.RoutineResponse;
import com.owoon.was.domain.routine.dto.response.RoutineSummaryResponse;
import com.owoon.was.domain.routine.service.RoutineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/routines")
public class RoutineController implements RoutineApi {

    private final RoutineService routineService;

    @Override
    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(
            @PathVariable Long userId,
            @RequestBody @Valid RoutineCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(routineService.createRoutine(userId, request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineSummaryResponse>> getRoutines(@PathVariable Long userId) {
        return ResponseEntity.ok(routineService.getRoutines(userId));
    }

    @Override
    @GetMapping("/{routineId}")
    public ResponseEntity<RoutineResponse> getRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineId
    ) {
        return ResponseEntity.ok(routineService.getRoutine(userId, routineId));
    }

    @Override
    @PutMapping("/{routineId}")
    public ResponseEntity<RoutineResponse> updateRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineId,
            @RequestBody @Valid RoutineCreateRequest request
    ) {
        return ResponseEntity.ok(routineService.updateRoutine(userId, routineId, request));
    }

    @Override
    @DeleteMapping("/{routineId}")
    public ResponseEntity<Void> deleteRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineId
    ) {
        routineService.deleteRoutine(userId, routineId);
        return ResponseEntity.noContent().build();
    }
}
