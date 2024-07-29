package com.hongseo.wanted_pre_onboarding.global.error.exception;

import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;

}