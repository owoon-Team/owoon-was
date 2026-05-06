package com.owoon.was.domain.user.controller;

import com.owoon.was.domain.user.controller.api.AuthApi;
import com.owoon.was.domain.user.dto.request.LoginRequest;
import com.owoon.was.domain.user.dto.request.UserCreateRequest;
import com.owoon.was.domain.user.dto.response.AuthResponse;
import com.owoon.was.domain.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }

    @Override
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    @GetMapping("/check-email")
    public ResponseEntity<Void> checkEmail(@RequestParam String email) {
        authService.checkEmailDuplication(email);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/sign-out")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
