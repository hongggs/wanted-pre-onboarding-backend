package com.hongseo.wanted_pre_onboarding.domain.jobapplication.service;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.dto.request.JobApplicationRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.model.JobApplication;

/**
 * 지원 관련 비즈니스 로직을 정의하는 서비스 인터페이스
 */
public interface JobApplicationService {
    public Long apply(JobApplicationRequestDto jobApplicationDto);
}
