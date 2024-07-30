package com.hongseo.wanted_pre_onboarding.domain.jobposting.service;

import com.hongseo.wanted_pre_onboarding.domain.company.error.exception.CompanyNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingUpdateResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.error.exception.JobPostingNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.adapter.JobPostingAndDtoAdapter;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.repository.JobPostingRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobPostingServiceImpl implements JobPostingService{

    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    @Transactional
    public Long createJobPosting(JobPostingCreateRequestDto jobPostingDto) {
        Company company = companyRepository.findById(jobPostingDto.getCompanyId())
                .orElseThrow(CompanyNotFoundException::new);
        JobPosting save = jobPostingRepository.save(JobPostingAndDtoAdapter.dtoToEntity(company, jobPostingDto));

        return save.getJobPostingId();
    }

    @Override
    @Transactional
    public JobPostingUpdateResponseDto updateJobPosting(Long id, JobPostingUpdateRequestDto jobPostingDto) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(JobPostingNotFoundException::new);

        JobPosting save = jobPostingRepository.save(
                JobPostingAndDtoAdapter.updateDtoToEntity(jobPosting.getCompany(), id, jobPostingDto));

        return JobPostingAndDtoAdapter.entityToUpdateDto(save);
    }

    @Override
    @Transactional
    public void deleteJobPosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(JobPostingNotFoundException::new);
        jobPostingRepository.deleteById(jobPostingId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobPostingReadResponseDto> getAllJobPostings() {
        return jobPostingRepository.findAllWithCompany().stream()
                .map(JobPostingAndDtoAdapter::entityToReadDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobPostingReadResponseDto> searchJobPostingsByKeyword(String keyword) {
        return jobPostingRepository.findByKeyword(keyword).stream()
                .map(JobPostingAndDtoAdapter::entityToReadDto)
                .collect(Collectors.toList());
    }
}
