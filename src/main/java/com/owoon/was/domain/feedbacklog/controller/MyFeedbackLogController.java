package com.owoon.was.domain.feedbacklog.controller;

import com.owoon.was.domain.feedbacklog.controller.api.MyFeedbackLogApi;
import com.owoon.was.domain.feedbacklog.dto.response.FeedbackLogResponse;
import com.owoon.was.domain.feedbacklog.service.FeedbackLogService;
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
@RequestMapping("/api/v1/routine-sessions/me/{sessionId}/exercise-results/{resultId}/feedback-logs")
public class MyFeedbackLogController implements MyFeedbackLogApi {

    private final FeedbackLogService feedbackLogService;

    @Override
    @GetMapping
    public ResponseEntity<List<FeedbackLogResponse>> getMyFeedbackLogs(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long sessionId,
            @PathVariable Long resultId
    ) {
        return ResponseEntity.ok(feedbackLogService.getFeedbackLogs(userDetails.getUserId(), sessionId, resultId));
    }
}
