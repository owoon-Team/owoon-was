package com.owoon.was.domain.exercise.controller.api;

import com.owoon.was.domain.exercise.dto.request.ExerciseCreateRequest;
import com.owoon.was.domain.exercise.dto.response.ExerciseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Exercise API", description = "운동 종류 관련 API")
public interface ExerciseApi {

    @Operation(summary = "운동 종류 등록", description = "루틴 생성 시 선택할 수 있는 운동 종류를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = ExerciseResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "409", description = "이미 등록된 운동 코드")
    })
    ResponseEntity<ExerciseResponse> createExercise(
            @Parameter(description = "운동 종류 등록 정보")
            @RequestBody @Valid ExerciseCreateRequest request
    );

    @Operation(summary = "운동 종류 목록 조회", description = "루틴 생성 시 선택할 수 있는 운동 종류 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ExerciseResponse.class)))
    })
    ResponseEntity<List<ExerciseResponse>> getExercises();
}
