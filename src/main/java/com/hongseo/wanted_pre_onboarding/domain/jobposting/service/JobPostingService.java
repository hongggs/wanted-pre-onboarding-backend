package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import java.util.List;

/**
 * 채용공고 관련 비즈니스 로직을 정의하는 서비스 인터페이스
 */
public interface JobPostingService {
    public Long createJobPosting(JobPostingCreateRequestDto jobPostingDto);
    public JobPostingUpdateResponseDto updateJobPosting(Long jobPostingId, JobPostingUpdateRequestDto jobPostingDto);

    public void deleteJobPosting(Long jobPostingId);
    public List<JobPostingReadResponseDto> getAllJobPostings();
    public List<JobPostingReadResponseDto> searchJobPostingsByKeyword(String keyword);
    public JobPostingReadDetailResponseDto getJobPostingDetail(Long jobPostingId);
}
