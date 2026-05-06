package com.owoon.was.domain.user.controller.api;

import com.owoon.was.domain.user.dto.request.LoginRequest;
import com.owoon.was.domain.user.dto.request.UserCreateRequest;
import com.owoon.was.domain.user.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth API", description = "인증 관련 API")
public interface AuthApi {

    @Operation(summary = "회원가입", description = "회원가입 후 access token을 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "409", description = "이미 사용 중인 이메일",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                      "status": 409,
                                      "message": "이미 사용 중인 이메일입니다."
                                    }
                                    """)
                    }))
    })
    ResponseEntity<AuthResponse> signup(
            @Parameter(description = "회원가입 정보")
            @RequestBody @Valid UserCreateRequest request
    );

    @Operation(summary = "로그인", description = "이메일과 비밀번호를 검증한 뒤 access token을 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "비밀번호 불일치"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<AuthResponse> login(
            @Parameter(description = "로그인 정보")
            @RequestBody @Valid LoginRequest request
    );

    @Operation(summary = "이메일 중복 확인", description = "사용 가능한 이메일인지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용 가능한 이메일"),
            @ApiResponse(responseCode = "409", description = "이미 사용 중인 이메일")
    })
    ResponseEntity<Void> checkEmail(
            @Parameter(description = "확인할 이메일", required = true, example = "user@owoon.com")
            @RequestParam String email
    );

    @Operation(summary = "로그아웃", description = "클라이언트가 보관 중인 토큰을 삭제하면 로그아웃됩니다.")
    @ApiResponse(responseCode = "200", description = "로그아웃 성공")
    ResponseEntity<Void> logout();
}
