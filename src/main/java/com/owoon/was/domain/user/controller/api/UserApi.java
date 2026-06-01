package com.owoon.was.domain.user.controller.api;

import com.owoon.was.domain.user.dto.request.UserProfileCreateRequest;
import com.owoon.was.domain.user.dto.response.UserProfileResponse;
import com.owoon.was.domain.user.dto.response.UserResponse;
import com.owoon.was.security.userdetails.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User API", description = "회원 관련 API")
public interface UserApi {

    @Operation(summary = "내 기본 정보 조회", description = "Authorization header의 access token으로 내 기본 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<UserResponse> getMyUser(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "내 신체 정보 등록", description = "Authorization header의 access token으로 내 신체 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
            @ApiResponse(responseCode = "409", description = "이미 등록된 신체 정보")
    })
    ResponseEntity<UserProfileResponse> createMyUserProfile(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "회원 신체 정보")
            @RequestBody @Valid UserProfileCreateRequest request
    );

    @Operation(summary = "내 신체 정보 조회", description = "Authorization header의 access token으로 내 신체 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "회원 또는 신체 정보를 찾을 수 없음")
    })
    ResponseEntity<UserProfileResponse> getMyUserProfile(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "회원 기본 정보 조회", description = "회원 ID로 기본 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<UserResponse> getUser(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId
    );

    @Operation(summary = "회원 신체 정보 등록", description = "회원의 운동 수준, 생년월일, 키, 몸무게, 성별, 주요 목표를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
            @ApiResponse(responseCode = "409", description = "이미 등록된 신체 정보")
    })
    ResponseEntity<UserProfileResponse> createUserProfile(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "회원 신체 정보")
            @RequestBody @Valid UserProfileCreateRequest request
    );

    @Operation(summary = "회원 신체 정보 조회", description = "회원 ID로 신체 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 또는 신체 정보를 찾을 수 없음")
    })
    ResponseEntity<UserProfileResponse> getUserProfile(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @PathVariable Long userId
    );
}
