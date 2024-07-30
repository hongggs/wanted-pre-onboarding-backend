package com.hongseo.wanted_pre_onboarding.domain.jobposting.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.error.errorcode.JobPostingErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

public class JobPostingNotFoundException extends RestApiException {
    public JobPostingNotFoundException() {
        super(JobPostingErrorCode.JOB_POSTING_NOT_FOUND);
    }
}
