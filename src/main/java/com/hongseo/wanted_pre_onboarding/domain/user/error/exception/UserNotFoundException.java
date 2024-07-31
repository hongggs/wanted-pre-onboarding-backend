package com.hongseo.wanted_pre_onboarding.domain.user.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.user.error.errorcode.UserErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

/**
 * 채용공고를 찾을 수 없을 때 발생하는 예외 클래스
 */
public class UserNotFoundException extends RestApiException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
