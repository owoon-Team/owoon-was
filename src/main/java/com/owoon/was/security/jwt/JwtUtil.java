package com.owoon.was.security.jwt;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String BEARER_PREFIX = "Bearer ";

    private final SecretKey secretKey;
    private final long accessTokenExpireMs;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expire-ms}") long accessTokenExpireMs
    ) {
        this.secretKey = Keys.hmacShaKeyFor(sha256(secret));
        this.accessTokenExpireMs = accessTokenExpireMs;
    }

    /**
     * 회원 식별 정보로 access token을 생성한다.
     */
    public String createAccessToken(Long userId, String email) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + accessTokenExpireMs);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiresAt)
                .signWith(secretKey)
                .compact();
    }

    /**
     * Authorization header의 bearer token에서 회원 ID를 추출한다.
     */
    public Long getUserId(String authorizationHeader) {
        String token = resolveBearerToken(authorizationHeader);

        try {
            return Long.valueOf(getClaims(token).getSubject());
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    /**
     * 요청에서 bearer token만 추출한다.
     */
    public String resolveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            return null;
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length());
        if (token.isBlank()) {
            return null;
        }

        return token;
    }

    /**
     * 토큰의 유효성을 검증한다.
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 토큰에서 이메일 claim을 추출한다.
     */
    public String getEmail(String token) {
        try {
            return getClaims(token).get("email", String.class);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    private String resolveBearerToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length());
        if (token.isBlank()) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return token;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private byte[] sha256(String secret) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(secret.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm is not available.", e);
        }
    }
}
