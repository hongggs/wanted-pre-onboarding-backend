package com.hongseo.wanted_pre_onboarding.domain.jobposting.model.adapter;

import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;

public class JobPostingAndDtoAdapter {

    public static JobPosting dtoToEntity(Company company, JobPostingRequestDto jobPostingDto) {
        return JobPosting.builder()
                .company(company)
                .position(jobPostingDto.getPosition())
                .reward(jobPostingDto.getReward())
                .description(jobPostingDto.getDescription())
                .skill(jobPostingDto.getSkill())
                .build();
    }

    public static JobPosting updateDtoToEntity(Company company, Long id, JobPostingUpdateRequestDto jobPostingDto) {

        return JobPosting.builder()
                .jobPostingId(id)
                .company(company)
                .position(jobPostingDto.getPosition())
                .reward(jobPostingDto.getReward())
                .description(jobPostingDto.getDescription())
                .skill(jobPostingDto.getSkill())
                .build();
    }

    public static JobPostingUpdateResponseDto entityToUpdateDto(JobPosting jobPosting) {
        return JobPostingUpdateResponseDto.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .position(jobPosting.getPosition())
                .reward(jobPosting.getReward())
                .description(jobPosting.getDescription())
                .skill(jobPosting.getSkill())
                .build();
    }
}
