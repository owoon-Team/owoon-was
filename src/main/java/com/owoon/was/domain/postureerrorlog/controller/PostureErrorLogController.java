package com.owoon.was.domain.postureerrorlog.controller;

import com.owoon.was.domain.postureerrorlog.controller.api.PostureErrorLogApi;
import com.owoon.was.domain.postureerrorlog.dto.response.PostureErrorLogResponse;
import com.owoon.was.domain.postureerrorlog.service.PostureErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/routine-sessions/{sessionId}/exercise-results/{resultId}/posture-error-logs")
public class PostureErrorLogController implements PostureErrorLogApi {

    private final PostureErrorLogService postureErrorLogService;

    @Override
    @GetMapping
    public ResponseEntity<List<PostureErrorLogResponse>> getPostureErrorLogs(
            @PathVariable Long userId,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        return ResponseEntity.ok(postureErrorLogService.getPostureErrorLogs(userId, sessionId, resultId));
    }
}
