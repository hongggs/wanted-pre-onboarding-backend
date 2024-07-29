package com.hongseo.wanted_pre_onboarding.domain.company.error.exception;

import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

public class CompanyNotFoundException extends RestApiException {
    public CompanyNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
