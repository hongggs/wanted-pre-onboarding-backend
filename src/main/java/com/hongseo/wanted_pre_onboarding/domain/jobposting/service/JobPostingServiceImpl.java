package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.company.exception.CompanyNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.adapter.JobPostingAndDtoAdapter;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.repository.JobPostingRepository;
import com.hongseo.wanted_pre_onboarding.global.error.errorcode.CommonErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobPostingServiceImpl implements JobPostingService{

    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Long createJobPosting(JobPostingRequestDto jobPostingDto) {
        Company company = companyRepository.findById(jobPostingDto.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException(CommonErrorCode.RESOURCE_NOT_FOUND));
        JobPosting save = jobPostingRepository.save(JobPostingAndDtoAdapter.dtoToEntity(company, jobPostingDto));

        return save.getJobPostingId();
    }
}
