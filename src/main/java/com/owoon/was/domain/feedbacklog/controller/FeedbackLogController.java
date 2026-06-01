package com.owoon.was.domain.feedbacklog.controller;

import com.owoon.was.domain.feedbacklog.controller.api.FeedbackLogApi;
import com.owoon.was.domain.feedbacklog.dto.response.FeedbackLogResponse;
import com.owoon.was.domain.feedbacklog.service.FeedbackLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/routine-sessions/{sessionId}/exercise-results/{resultId}/feedback-logs")
public class FeedbackLogController implements FeedbackLogApi {

    private final FeedbackLogService feedbackLogService;

    @Override
    @GetMapping
    public ResponseEntity<List<FeedbackLogResponse>> getFeedbackLogs(
            @PathVariable Long userId,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        return ResponseEntity.ok(feedbackLogService.getFeedbackLogs(userId, sessionId, resultId));
    }
}
