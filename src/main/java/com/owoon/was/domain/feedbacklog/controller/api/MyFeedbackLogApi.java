package com.owoon.was.domain.feedbacklog.controller.api;

import com.owoon.was.domain.feedbacklog.dto.response.FeedbackLogResponse;
import com.owoon.was.security.userdetails.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Feedback Log API", description = "피드백 로그 관련 API")
public interface MyFeedbackLogApi {

    @Operation(summary = "내 피드백 로그 목록 조회", description = "Authorization header의 access token으로 내 피드백 로그 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = FeedbackLogResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 인증 토큰"),
            @ApiResponse(responseCode = "404", description = "회원 또는 운동별 실행 결과를 찾을 수 없음")
    })
    ResponseEntity<List<FeedbackLogResponse>> getMyFeedbackLogs(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "루틴 실행 기록 ID", required = true, example = "1")
            @PathVariable Long sessionId,
            @Parameter(description = "운동별 실행 결과 ID", required = true, example = "1")
            @PathVariable Long resultId
    );
}
