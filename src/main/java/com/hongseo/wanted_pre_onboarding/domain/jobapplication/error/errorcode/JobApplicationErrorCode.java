package com.hongseo.wanted_pre_onboarding.domain.jobapplication.error.errorcode;

import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 지원 관련 에러코드 정의
 */
@Getter
@RequiredArgsConstructor
public enum JobApplicationErrorCode implements ErrorCode {
    DUP_JOB_APPLY(HttpStatus.BAD_REQUEST, "사용자는 1회만 지원 가능합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
