package com.hongseo.wanted_pre_onboarding.domain.jobposting.repository;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

}
