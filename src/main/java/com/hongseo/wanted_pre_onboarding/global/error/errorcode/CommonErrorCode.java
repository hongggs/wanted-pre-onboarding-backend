package com.hongseo.wanted_pre_onboarding.global.error.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 애플리케이션에서 사용하는 공통 에러 코드를 정의
 */
@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"), // 잘못된 파라미터가 포함된 요청
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"), // 요청한 리소스가 존재하지 않을 때 사용
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"), // 서버 내부 오류가 발생했을 경우에 사용
    ;

    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String message; // 클라이언트에 전달될 오류 메시지
}
