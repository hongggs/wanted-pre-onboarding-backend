package com.hongseo.wanted_pre_onboarding.domain.jobapplication.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.error.errorcode.JobApplicationErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

public class DuplicateJobApplicationException extends RestApiException {
    public DuplicateJobApplicationException() {
        super(JobApplicationErrorCode.DUP_JOB_APPLY);
    }
}
