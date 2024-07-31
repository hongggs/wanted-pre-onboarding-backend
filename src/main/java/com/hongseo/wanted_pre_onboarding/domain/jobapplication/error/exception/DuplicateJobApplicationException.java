package com.hongseo.wanted_pre_onboarding.domain.jobapplication.error.exception;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.error.errorcode.JobApplicationErrorCode;
import com.hongseo.wanted_pre_onboarding.global.error.exception.RestApiException;

/**
 * 같은 지원공고에 한 지원자가 중복 지원할 경우 발생하는 예외 클래스
 */
public class DuplicateJobApplicationException extends RestApiException {
    public DuplicateJobApplicationException() {
        super(JobApplicationErrorCode.DUP_JOB_APPLY);
    }
}
