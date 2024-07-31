package com.hongseo.wanted_pre_onboarding.domain.jobposting.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.error.errorcode.JobPostingErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

/**
 * 채용공고를 찾을 수 없을 때 발생하는 예외 클래스
 */
public class JobPostingNotFoundException extends RestApiException {
    public JobPostingNotFoundException() {
        super(JobPostingErrorCode.JOB_POSTING_NOT_FOUND);
    }
}
