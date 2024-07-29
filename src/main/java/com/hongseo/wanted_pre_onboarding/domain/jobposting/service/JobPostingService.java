package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;

public interface JobPostingService {
    public Long createJobPosting(JobPostingRequestDto jobPostingDto);
}
