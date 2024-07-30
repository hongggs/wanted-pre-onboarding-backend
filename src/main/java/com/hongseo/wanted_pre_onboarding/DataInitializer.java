package com.hongseo.wanted_pre_onboarding;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import com.hongseo.wanted_pre_onboarding.domain.company.service.CompanyService;
import com.hongseo.wanted_pre_onboarding.domain.company.service.CompanyServiceImpl;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.service.JobPostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer {

    private final CompanyService companyService;
    private final JobPostingService jobPostingService;

    public DataInitializer(CompanyService companyService, JobPostingService jobPostingService) {
        this.companyService = companyService;
        this.jobPostingService = jobPostingService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        companyService.createCompany(new CompanyRequestDto("회샤A", "한국", "서울"));
        companyService.createCompany(new CompanyRequestDto("회사B", "한국", "판교"));

//        jobPostingService.createJobPosting(new JobPostingRequestDto(1L, ))
    }
}