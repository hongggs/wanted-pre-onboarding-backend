package com.hongseo.wanted_pre_onboarding;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.service.CompanyService;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.service.JobPostingService;
import com.hongseo.wanted_pre_onboarding.domain.user.model.User;
import com.hongseo.wanted_pre_onboarding.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer {

    private final CompanyService companyService;
    private final JobPostingService jobPostingService;
    private final UserRepository userRepository;

    public DataInitializer(CompanyService companyService, JobPostingService jobPostingService,
                           UserRepository userRepository) {
        this.companyService = companyService;
        this.jobPostingService = jobPostingService;
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        companyService.createCompany(new CompanyRequestDto("회샤A", "한국", "서울"));
        companyService.createCompany(new CompanyRequestDto("회사B", "한국", "판교"));

        jobPostingService.createJobPosting(new JobPostingCreateRequestDto(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자 구합니다.", "자바, 스프링"));
        jobPostingService.createJobPosting(new JobPostingCreateRequestDto(2L, "AI 개발자", 1000000, "성실한 AI 개발자 구합니다.", "파이썬"));

        userRepository.save(new User(null, "abc@gmail.com", "김이름"));
        userRepository.save(new User(null, "qwer@gmail.com", "손이름"));
    }
}