package com.owoon.was.domain.user.controller;

import com.owoon.was.domain.user.controller.api.UserApi;
import com.owoon.was.domain.user.dto.request.UserProfileCreateRequest;
import com.owoon.was.domain.user.dto.response.UserProfileResponse;
import com.owoon.was.domain.user.dto.response.UserResponse;
import com.owoon.was.domain.user.service.UserService;
import com.owoon.was.security.userdetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements UserApi {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(userService.getUser(userDetails.getUserId()));
    }

    @PostMapping("/me/profile")
    public ResponseEntity<UserProfileResponse> createMyUserProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid UserProfileCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUserProfile(userDetails.getUserId(), request));
    }

    @GetMapping("/me/profile")
    public ResponseEntity<UserProfileResponse> getMyUserProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(userService.getUserProfile(userDetails.getUserId()));
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    @PostMapping("/{userId}/profile")
    public ResponseEntity<UserProfileResponse> createUserProfile(
            @PathVariable Long userId,
            @RequestBody @Valid UserProfileCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUserProfile(userId, request));
    }

    @Override
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }
}
