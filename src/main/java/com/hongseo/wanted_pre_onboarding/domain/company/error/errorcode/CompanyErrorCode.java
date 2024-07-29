package com.hongseo.wanted_pre_onboarding.domain.company.error.errorcode;

import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CompanyErrorCode implements ErrorCode {
    COMPANY_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 Company를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
