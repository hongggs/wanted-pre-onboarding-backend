package com.hongseo.wanted_pre_onboarding.domain.company.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.company.error.errorcode.CompanyErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.errorcode.ErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

/**
 * 회사를 찾을 수 없을 때 발생하는 예외 클래스
 */
public class CompanyNotFoundException extends RestApiException {
    public CompanyNotFoundException() {
        super(CompanyErrorCode.COMPANY_NOT_FOUND);
    }
}
