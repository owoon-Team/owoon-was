package com.owoon.was.domain.user.dto.response;

import com.owoon.was.domain.user.entity.User;

public record AuthResponse(
        Long id,
        String email,
        String name,
        String accessToken
) {

    public static AuthResponse from(User user, String accessToken) {
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                accessToken
        );
    }
}
