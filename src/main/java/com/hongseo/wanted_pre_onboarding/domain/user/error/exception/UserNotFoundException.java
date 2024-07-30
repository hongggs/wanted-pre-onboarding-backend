package com.hongseo.wanted_pre_onboarding.domain.user.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.user.error.errorcode.UserErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

public class UserNotFoundException extends RestApiException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
