package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import java.util.List;

public interface JobPostingService {
    public Long createJobPosting(JobPostingCreateRequestDto jobPostingDto);
    public JobPostingUpdateResponseDto updateJobPosting(Long id, JobPostingUpdateRequestDto jobPostingDto);

    public void deleteJobPosting(Long jobPostingId);
    public List<JobPostingReadResponseDto> getAllJobPostings();
    public List<JobPostingReadResponseDto> searchJobPostingsByKeyword(String keyword);
    public JobPostingReadDetailResponseDto getJobPostingDetail(Long jobPostingId);
}
