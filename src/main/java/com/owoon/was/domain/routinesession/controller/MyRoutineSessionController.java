package com.owoon.was.domain.routinesession.controller;

import com.owoon.was.common.security.jwt.JwtUtil;
import com.owoon.was.domain.routinesession.controller.api.MyRoutineSessionApi;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routine-sessions/me")
public class MyRoutineSessionController implements MyRoutineSessionApi {

    private final RoutineSessionService routineSessionService;
    private final JwtUtil jwtUtil;

    @Override
    @PostMapping
    public ResponseEntity<RoutineSessionResponse> createMyRoutineSession(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid RoutineSessionCreateRequest request
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(routineSessionService.createRoutineSession(userId, request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineSessionSummaryResponse>> getMyRoutineSessions(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.ok(routineSessionService.getRoutineSessions(userId));
    }

    @Override
    @GetMapping("/{sessionId}")
    public ResponseEntity<RoutineSessionResponse> getMyRoutineSession(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long sessionId
    ) {
        Long userId = jwtUtil.getUserId(authorizationHeader);
        return ResponseEntity.ok(routineSessionService.getRoutineSession(userId, sessionId));
    }
}
