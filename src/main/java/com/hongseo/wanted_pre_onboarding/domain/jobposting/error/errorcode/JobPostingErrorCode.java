package com.hongseo.wanted_pre_onboarding.domain.jobposting.error.errorcode;

import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 채용공고 관련 에러코드 정의
 */
@Getter
@RequiredArgsConstructor
public enum JobPostingErrorCode implements ErrorCode {
    JOB_POSTING_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 채용공고를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
