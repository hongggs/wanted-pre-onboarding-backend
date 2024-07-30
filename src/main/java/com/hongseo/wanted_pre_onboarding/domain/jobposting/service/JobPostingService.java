package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;

public interface JobPostingService {
    public Long createJobPosting(JobPostingCreateRequestDto jobPostingDto);
    public JobPostingUpdateResponseDto updateJobPosting(Long id, JobPostingUpdateRequestDto jobPostingDto);

    public void deleteJobPosting(Long jobPostingId);
}
