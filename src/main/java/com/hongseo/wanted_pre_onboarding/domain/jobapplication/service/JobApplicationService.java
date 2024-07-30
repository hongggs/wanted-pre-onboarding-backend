package com.hongseo.wanted_pre_onboarding.domain.jobapplication.service;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.dto.request.JobApplicationRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.model.JobApplication;

public interface JobApplicationService {
    public Long apply(JobApplicationRequestDto jobApplicationDto);
}
