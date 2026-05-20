package com.owoon.was.domain.routinesession.controller;

import com.owoon.was.domain.routinesession.controller.api.RoutineSessionApi;
import com.owoon.was.domain.routinesession.dto.request.RoutineSessionCreateRequest;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionResponse;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionSummaryResponse;
import com.owoon.was.domain.routinesession.service.RoutineSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/routine-sessions")
public class RoutineSessionController implements RoutineSessionApi {

    private final RoutineSessionService routineSessionService;

    @Override
    @PostMapping
    public ResponseEntity<RoutineSessionResponse> createRoutineSession(
            @PathVariable Long userId,
            @RequestBody @Valid RoutineSessionCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(routineSessionService.createRoutineSession(userId, request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineSessionSummaryResponse>> getRoutineSessions(@PathVariable Long userId) {
        return ResponseEntity.ok(routineSessionService.getRoutineSessions(userId));
    }

    @Override
    @GetMapping("/{sessionId}")
    public ResponseEntity<RoutineSessionResponse> getRoutineSession(
            @PathVariable Long userId,
            @PathVariable Long sessionId
    ) {
        return ResponseEntity.ok(routineSessionService.getRoutineSession(userId, sessionId));
    }
}
