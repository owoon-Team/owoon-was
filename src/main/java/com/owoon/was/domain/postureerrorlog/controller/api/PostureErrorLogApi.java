package com.owoon.was.domain.postureerrorlog.controller.api;

import com.owoon.was.domain.postureerrorlog.dto.response.PostureErrorLogResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Posture Error Log API", description = "자세 오류 로그 관련 API")
public interface PostureErrorLogApi {

    @Operation(summary = "자세 오류 로그 목록 조회", description = "회원 ID와 루틴 실행 기록 ID, 운동별 실행 결과 ID로 자세 오류 로그 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = PostureErrorLogResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 또는 운동별 실행 결과를 찾을 수 없음")
    })
    ResponseEntity<List<PostureErrorLogResponse>> getPostureErrorLogs(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 실행 기록 ID", required = true, example = "1")
            @PathVariable Long sessionId,
            @Parameter(description = "운동별 실행 결과 ID", required = true, example = "1")
            @PathVariable Long resultId
    );
}
