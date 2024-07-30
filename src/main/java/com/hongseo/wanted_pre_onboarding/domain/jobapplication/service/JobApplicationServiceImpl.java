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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

    @Autowired
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository,
                                 JobPostingRepository jobPostingRepository,
                                 UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobPostingRepository = jobPostingRepository;
        this.userRepository = userRepository;
    }


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
