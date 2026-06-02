package com.owoon.was.domain.routinesession.controller;

import com.owoon.was.domain.routinesession.controller.api.MyRoutineSessionApi;
import com.owoon.was.domain.routinesession.dto.request.RoutineSessionCreateRequest;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionResponse;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionSummaryResponse;
import com.owoon.was.domain.routinesession.service.RoutineSessionService;
import com.owoon.was.security.userdetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routine-sessions/me")
public class MyRoutineSessionController implements MyRoutineSessionApi {

    private final RoutineSessionService routineSessionService;

    @Override
    @PostMapping
    public ResponseEntity<RoutineSessionResponse> createMyRoutineSession(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid RoutineSessionCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(routineSessionService.createRoutineSession(userDetails.getUserId(), request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineSessionSummaryResponse>> getMyRoutineSessions(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(routineSessionService.getRoutineSessions(userDetails.getUserId()));
    }

    @Override
    @GetMapping("/{sessionId}")
    public ResponseEntity<RoutineSessionResponse> getMyRoutineSession(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long sessionId
    ) {
        return ResponseEntity.ok(routineSessionService.getRoutineSession(userDetails.getUserId(), sessionId));
    }
}
