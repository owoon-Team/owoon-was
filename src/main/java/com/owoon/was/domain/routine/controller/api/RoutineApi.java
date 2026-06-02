package com.owoon.was.domain.routine.controller.api;

import com.owoon.was.domain.routine.dto.request.RoutineCreateRequest;
import com.owoon.was.domain.routine.dto.response.RoutineResponse;
import com.owoon.was.domain.routine.dto.response.RoutineSummaryResponse;
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

@Tag(name = "Routine API", description = "루틴 관련 API")
public interface RoutineApi {

    @Operation(summary = "루틴 생성", description = "회원이 수행할 운동 목록과 목표치를 묶어 루틴을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공",
                    content = @Content(schema = @Schema(implementation = RoutineResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "404", description = "회원 또는 운동을 찾을 수 없음"),
            @ApiResponse(responseCode = "409", description = "루틴 내 운동 순서 중복")
    })
    ResponseEntity<RoutineResponse> createRoutine(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 생성 정보")
            @RequestBody @Valid RoutineCreateRequest request
    );

    @Operation(summary = "루틴 목록 조회", description = "회원 ID로 루틴 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineSummaryResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<List<RoutineSummaryResponse>> getRoutines(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId
    );

    @Operation(summary = "루틴 상세 조회", description = "회원 ID와 루틴 ID로 루틴 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = RoutineResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴을 찾을 수 없음")
    })
    ResponseEntity<RoutineResponse> getRoutine(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 ID", required = true, example = "1")
            @PathVariable Long routineId
    );

    @Operation(summary = "루틴 수정", description = "회원 ID와 루틴 ID로 루틴 기본 정보와 포함 운동 목록을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = RoutineResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "404", description = "회원, 루틴 또는 운동을 찾을 수 없음"),
            @ApiResponse(responseCode = "409", description = "루틴 내 운동 순서 중복")
    })
    ResponseEntity<RoutineResponse> updateRoutine(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 ID", required = true, example = "1")
            @PathVariable Long routineId,
            @Parameter(description = "수정할 루틴 정보")
            @RequestBody @Valid RoutineCreateRequest request
    );

    @Operation(summary = "루틴 삭제", description = "회원 ID와 루틴 ID로 루틴을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "회원 또는 루틴을 찾을 수 없음")
    })
    ResponseEntity<Void> deleteRoutine(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "루틴 ID", required = true, example = "1")
            @PathVariable Long routineId
    );
}
