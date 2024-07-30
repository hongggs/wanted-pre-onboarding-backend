package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hongseo.wanted_pre_onboarding.domain.company.error.exception.CompanyNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.error.exception.JobPostingNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class JobPostingServiceTest {
    @Mock
    private JobPostingRepository jobPostingRepository;
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private JobPostingServiceImpl jobPostingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("채용 공고 정상 등록")
    void createJobPosting_ShouldReturnJobPostingId() {
        //Given
        Company company = new Company(1L, "네입어", "대한민국", "판교");
        JobPostingRequestDto dto = new JobPostingRequestDto(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자를 뽑습니다.", "Java, Spring Boot");
        when(companyRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(company));
        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(new JobPosting(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자를 뽑습니다.", "Java, Spring Boot", company));

        //When
        Long jobPostingId = jobPostingService.createJobPosting(dto);

        //Then
        assertNotNull(jobPostingId);
        verify(jobPostingRepository).save(any(JobPosting.class)); //db에 저장되었는지를 확인
    }

    @Test
    @DisplayName("존재하지 않는 회사 ID에 대한 채용 공고 등록 시 예외 발생")
    void createJobPosting_WithNonexistentCompanyId() {
        //Given
        JobPostingRequestDto dto = new JobPostingRequestDto(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자를 뽑습니다.", "Java, Spring Boot");

        when(companyRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        //When, Then
        assertThrows(CompanyNotFoundException.class, () -> {
            jobPostingService.createJobPosting(dto);
        });
    }

    @Test
    @DisplayName("존재하지 않는 채용공고 ID에 대한 채용 공고 수정 시 예외 발생")
    void updateJobPosting_WithNonexistentJobPostingId() {
        //Given
        JobPostingUpdateRequestDto dto = new JobPostingUpdateRequestDto("백엔드 개발자", 500000, "경험 많은 백엔드 개발자를 찾습니다.", "Java, Spring Boot");
        when(jobPostingRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        //When, Then
        assertThrows(JobPostingNotFoundException.class, () -> {
            jobPostingService.updateJobPosting(1L, dto);
        });
    }
}