package com.owoon.was.common.exception.error;

public enum ErrorCode {

    // 서버 오류
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류입니다."),

    // 공통 오류
    INVALID_INPUT_VALUE(400, "입력값이 올바르지 않습니다."),
    FORBIDDEN_ACCESS(403, "접근 권한이 없습니다."),
    RESOURCE_NOT_FOUND(404, "요청한 리소스를 찾을 수 없습니다."),

    // 회원 오류
    USER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),
    USER_PROFILE_NOT_FOUND(404, "회원 신체 정보를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(409, "이미 사용 중인 이메일입니다."),
    DUPLICATE_USER_PROFILE(409, "이미 등록된 회원 신체 정보입니다."),
    INCORRECT_PASSWORD(401, "비밀번호가 일치하지 않습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
