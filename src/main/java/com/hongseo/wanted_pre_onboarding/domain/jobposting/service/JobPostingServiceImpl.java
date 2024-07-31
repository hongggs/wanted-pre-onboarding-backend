package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.company.error.exception.CompanyNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.model.JobApplication;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.repository.JobApplicationRepository;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.error.exception.JobPostingNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.adapter.JobPostingAndDtoAdapter;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.repository.JobPostingRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 채용공고 관련 서비스를 제공하는 클래스
 */
@AllArgsConstructor
@Service
public class JobPostingServiceImpl implements JobPostingService{

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    private final JobApplicationRepository jobApplicationRepository;

    /**
     * 새로운 채용공고 생성
     * @param jobPostingDto 채용공고 생성에 필요한 데이터를 담은 DTO
     * @return 생성된 채용공고의 ID
     */
    @Override
    @Transactional
    public Long createJobPosting(JobPostingCreateRequestDto jobPostingDto) {
        Company company = companyRepository.findById(jobPostingDto.getCompanyId())
                .orElseThrow(CompanyNotFoundException::new);
        JobPosting save = jobPostingRepository.save(JobPostingAndDtoAdapter.createDtoToEntity(company, jobPostingDto));

        return save.getJobPostingId();
    }

    /**
     * 주어진 ID의 채용공고 업데이트
     * @param jobPostingId 채용공고 ID
     * @param jobPostingDto 채용공고 업데이트에 필요한 데이터를 담은 DTO
     * @return 업데이트된 채용공고의 상세 정보를 반환
     */
    @Override
    @Transactional
    public JobPostingUpdateResponseDto updateJobPosting(Long jobPostingId, JobPostingUpdateRequestDto jobPostingDto) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(JobPostingNotFoundException::new);

        JobPosting save = jobPostingRepository.save(
                JobPostingAndDtoAdapter.updateDtoToEntity(jobPosting.getCompany(), jobPostingId, jobPostingDto));

        return JobPostingAndDtoAdapter.entityToUpdateDto(save);
    }

    /**
     * 주어진 ID의 채용공고 삭제
     * @param jobPostingId
     */
    @Override
    @Transactional
    public void deleteJobPosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(JobPostingNotFoundException::new);

        // 해당 JobPosting 참조하는 JobApplication들을 삭제
        List<JobApplication> applications = jobApplicationRepository.findByJobPosting(jobPosting);
        jobApplicationRepository.deleteAll(applications);

        jobPostingRepository.deleteById(jobPostingId);
    }

    /**
     * 모든 채용공고 조회
     * @return 채용공고 목록을 반환
     */
    @Override
    @Transactional(readOnly = true)
    public List<JobPostingReadResponseDto> getAllJobPostings() {
        return jobPostingRepository.findAllWithCompany().stream()
                .map(JobPostingAndDtoAdapter::entityToReadDto)
                .collect(Collectors.toList());
    }

    /**
     * 키워드로 채용공고 검색
     * @param keyword 검색 키워드
     * @return 검색된 채용공고 목록 반환
     */
    @Override
    @Transactional(readOnly = true)
    public List<JobPostingReadResponseDto> searchJobPostingsByKeyword(String keyword) {
        return jobPostingRepository.findByKeyword(keyword).stream()
                .map(JobPostingAndDtoAdapter::entityToReadDto)
                .collect(Collectors.toList());
    }

    /**
     * 주어진 ID의 채용공고 상세 정보를 조회
     * @param jobPostingId 상세 정보를 조회할 채용공고의 ID
     * @return 채용공고의 상세 정보를 반환
     */
    @Override
    @Transactional(readOnly = true)
    public JobPostingReadDetailResponseDto getJobPostingDetail(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(JobPostingNotFoundException::new);
        List<Long> otherJobPostings = jobPostingRepository.findAllJobPostingIdsByCompanyId(jobPosting.getCompany().getCompanyId(), jobPostingId);

        return JobPostingAndDtoAdapter.entityToReadDetailDto(otherJobPostings, jobPosting);
    }
}
