package com.owoon.was.domain.postureerrorlog.controller;

import com.owoon.was.domain.postureerrorlog.controller.api.MyPostureErrorLogApi;
import com.owoon.was.domain.postureerrorlog.dto.response.PostureErrorLogResponse;
import com.owoon.was.domain.postureerrorlog.service.PostureErrorLogService;
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
@RequestMapping("/api/v1/routine-sessions/me/{sessionId}/exercise-results/{resultId}/posture-error-logs")
public class MyPostureErrorLogController implements MyPostureErrorLogApi {

    private final PostureErrorLogService postureErrorLogService;

    @Override
    @GetMapping
    public ResponseEntity<List<PostureErrorLogResponse>> getMyPostureErrorLogs(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        return ResponseEntity.ok(postureErrorLogService.getPostureErrorLogs(userDetails.getUserId(), sessionId, resultId));
    }
}
