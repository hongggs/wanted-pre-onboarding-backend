package com.hongseo.wanted_pre_onboarding.domain.jobapplication.service;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.dto.request.JobApplicationRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.error.exception.DuplicateJobApplicationException;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.model.JobApplication;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.repository.JobApplicationRepository;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.error.exception.JobPostingNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.repository.JobPostingRepository;
import com.hongseo.wanted_pre_onboarding.domain.user.error.exception.UserNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.user.model.User;
import com.hongseo.wanted_pre_onboarding.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 지원 관련 서비스를 제공하는 클래스
 */
@AllArgsConstructor
@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

    /**
     * 사용자의 채용 지원 요청을 받아 처리
     * @param jobApplicationDto jobApplicationDto 채용 지원에 필요한 정보를 담은 DTO
     * @return 생성된 지원의 Id
     */
    @Override
    @Transactional
    public Long apply(JobApplicationRequestDto jobApplicationDto) {
        User user = userRepository.findById(jobApplicationDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        JobPosting jobPosting = jobPostingRepository.findById(jobApplicationDto.getJobPostingId())
                .orElseThrow(JobPostingNotFoundException::new);

        if (jobApplicationRepository.existsByJobPostingAndUser(jobPosting, user)) {
            throw new DuplicateJobApplicationException();
        }

        JobApplication jobApplication = new JobApplication(null, jobPosting, user);
        return jobApplicationRepository.save(jobApplication).getJobApplicationId();
    }
}
