package com.hongseo.wanted_pre_onboarding.domain.jobposting.model.adapter;

import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
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
}
