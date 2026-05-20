package com.owoon.was.domain.routinesession.controller.api;

import com.owoon.was.domain.routinesession.dto.request.RoutineSessionCreateRequest;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionResponse;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Routine Session API", description = "루틴 실행 기록 관련 API")
public interface RoutineSessionApi {

    @Operation(summary = "루틴 실행 기록 생성", description = "운동 종료 후 앱에서 전달한 루틴 실행 요약 결과를 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSessionResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴을 찾을 수 없음")
    })
    ResponseEntity<RoutineSessionResponse> createRoutineSession(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 실행 기록 생성 정보")
            @RequestBody @Valid RoutineSessionCreateRequest request
    );

    @Operation(summary = "루틴 실행 기록 목록 조회", description = "회원 ID로 루틴 실행 기록 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSessionSummaryResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<List<RoutineSessionSummaryResponse>> getRoutineSessions(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId
    );

    @Operation(summary = "루틴 실행 기록 상세 조회", description = "회원 ID와 루틴 실행 기록 ID로 루틴 실행 상세 결과를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSessionResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴 실행 기록을 찾을 수 없음")
    })
    ResponseEntity<RoutineSessionResponse> getRoutineSession(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 실행 기록 ID", required = true, example = "1")
            @PathVariable Long sessionId
    );
}
