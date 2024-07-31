package com.hongseo.wanted_pre_onboarding.global.error.exception;

import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 예외를 처리할 때 ErrorCode를 활용하기 위해 사용하는 사용자 정의 예외 클래스
 */
@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;

}