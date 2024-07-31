package com.hongseo.wanted_pre_onboarding.domain.jobposting.model.adapter;

import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import java.util.List;

/**
 * JobPosting 엔티티와 관련 DTOs 간의 데이터 변환을 처리하는 어댑터 클래스
 */
public class JobPostingAndDtoAdapter {

    public static JobPosting createDtoToEntity(Company company, JobPostingCreateRequestDto jobPostingDto) {
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

    public static JobPostingReadResponseDto entityToReadDto(JobPosting jobPosting) {
        return JobPostingReadResponseDto.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .companyName(jobPosting.getCompany().getCompanyName())
                .country(jobPosting.getCompany().getCountry())
                .region(jobPosting.getCompany().getRegion())
                .position(jobPosting.getPosition())
                .reward(jobPosting.getReward())
                .skill(jobPosting.getSkill())
                .createdAt(jobPosting.getCreatedAt())
                .build();
    }

    public static JobPostingReadDetailResponseDto entityToReadDetailDto(List<Long> otherJobPostings, JobPosting jobPosting) {
        return JobPostingReadDetailResponseDto.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .companyName(jobPosting.getCompany().getCompanyName())
                .country(jobPosting.getCompany().getCountry())
                .region(jobPosting.getCompany().getRegion())
                .position(jobPosting.getPosition())
                .reward(jobPosting.getReward())
                .description(jobPosting.getDescription())
                .skill(jobPosting.getSkill())
                .otherJobPostings(otherJobPostings)
                .createdAt(jobPosting.getCreatedAt())
                .build();
    }
}
