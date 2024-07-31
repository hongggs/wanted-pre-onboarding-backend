package com.hongseo.wanted_pre_onboarding.domain.jobapplication.repository;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.model.JobApplication;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import com.hongseo.wanted_pre_onboarding.domain.user.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 지원 데이터에 접근 제공하는 JPA 리포지토리 인터페이스
 */
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    /**
     * 특정 채용공고와 사용자에 대한 채용 지원 정보의 존재 여부를 확인
     * @param jobPosting jobPosting 존재 여부를 확인할 채용공고
     * @param user user 존재 여부를 확인할 사용자
     * @return 채용 지원이 이미 존재하는 경우 true, 그렇지 않은 경우 false
     */
    boolean existsByJobPostingAndUser(JobPosting jobPosting, User user);

    /**
     * 특정 채용공고에 대한 모든 채용 지원 정보를 조회
     * @param jobPosting 조회할 채용공고
     * @return 해당 채용공고에 지원한 모든 채용 지원 정보의 리스트
     */
    List<JobApplication> findByJobPosting(JobPosting jobPosting);
}
