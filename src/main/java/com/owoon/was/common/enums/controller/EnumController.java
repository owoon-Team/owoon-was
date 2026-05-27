package com.owoon.was.common.enums.controller;

import com.owoon.was.common.enums.controller.api.EnumApi;
import com.owoon.was.common.enums.dto.response.EnumGroupResponse;
import com.owoon.was.common.enums.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enums")
public class EnumController implements EnumApi {

    private final EnumService enumService;

    @Override
    @GetMapping
    public ResponseEntity<EnumGroupResponse> getEnums() {
        return ResponseEntity.ok(enumService.getEnums());
    }
}
