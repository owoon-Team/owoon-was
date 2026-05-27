package com.owoon.was.common.enums.controller.api;

import com.owoon.was.common.enums.dto.response.EnumGroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Enum API", description = "Enum 옵션 조회 API")
public interface EnumApi {

    @Operation(summary = "Enum 옵션 목록 조회", description = "화면 선택 옵션으로 사용하는 Enum 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = EnumGroupResponse.class)))
    })
    ResponseEntity<EnumGroupResponse> getEnums();
}
