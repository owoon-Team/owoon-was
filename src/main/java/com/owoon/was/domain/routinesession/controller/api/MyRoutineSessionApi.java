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
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "Routine Session API", description = "루틴 실행 기록 관련 API")
public interface MyRoutineSessionApi {

    @Operation(summary = "내 루틴 실행 기록 생성", description = "Authorization header의 access token으로 내 루틴 실행 요약 결과를 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSessionResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 인증 토큰"),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴을 찾을 수 없음")
    })
    ResponseEntity<RoutineSessionResponse> createMyRoutineSession(
            @Parameter(description = "Bearer access token", required = true)
            @RequestHeader("Authorization") String authorizationHeader,
            @Parameter(description = "루틴 실행 기록 생성 정보")
            @RequestBody @Valid RoutineSessionCreateRequest request
    );

    @Operation(summary = "내 루틴 실행 기록 목록 조회", description = "Authorization header의 access token으로 내 루틴 실행 기록 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSessionSummaryResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 인증 토큰"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<List<RoutineSessionSummaryResponse>> getMyRoutineSessions(
            @Parameter(description = "Bearer access token", required = true)
            @RequestHeader("Authorization") String authorizationHeader
    );

    @Operation(summary = "내 루틴 실행 기록 상세 조회", description = "Authorization header의 access token으로 내 루틴 실행 상세 결과를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSessionResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 인증 토큰"),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴 실행 기록을 찾을 수 없음")
    })
    ResponseEntity<RoutineSessionResponse> getMyRoutineSession(
            @Parameter(description = "Bearer access token", required = true)
            @RequestHeader("Authorization") String authorizationHeader,
            @Parameter(description = "루틴 실행 기록 ID", required = true, example = "1")
            @PathVariable Long sessionId
    );
}
