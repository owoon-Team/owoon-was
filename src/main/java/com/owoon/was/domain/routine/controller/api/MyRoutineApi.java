package com.owoon.was.domain.routine.controller.api;

import com.owoon.was.domain.routine.dto.response.RoutineResponse;
import com.owoon.was.domain.routine.dto.response.RoutineSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "Routine API", description = "루틴 관련 API")
public interface MyRoutineApi {

    @Operation(summary = "내 루틴 목록 조회", description = "Authorization header의 access token으로 내 루틴 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSummaryResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 인증 토큰"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<List<RoutineSummaryResponse>> getMyRoutines(
            @Parameter(description = "Bearer access token", required = true)
            @RequestHeader("Authorization") String authorizationHeader
    );

    @Operation(summary = "내 루틴 상세 조회", description = "Authorization header의 access token으로 내 루틴 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineResponse.class))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 인증 토큰"),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴을 찾을 수 없음")
    })
    ResponseEntity<RoutineResponse> getMyRoutine(
            @Parameter(description = "Bearer access token", required = true)
            @RequestHeader("Authorization") String authorizationHeader,
            @Parameter(description = "루틴 ID", required = true, example = "1")
            @PathVariable Long routineId
    );
}
