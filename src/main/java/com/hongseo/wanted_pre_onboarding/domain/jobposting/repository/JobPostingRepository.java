package com.hongseo.wanted_pre_onboarding.domain.jobposting.repository;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    @Query("SELECT jp FROM JobPosting jp JOIN FETCH jp.company ORDER BY jp.createdAt DESC")
    List<JobPosting> findAllWithCompany();

    @Query("SELECT jp FROM JobPosting jp JOIN jp.company c WHERE " +
            "LOWER(c.companyName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.country) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.region) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(jp.position) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(jp.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(jp.skill) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY jp.createdAt DESC")
    List<JobPosting> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT jp.jobPostingId FROM JobPosting jp WHERE jp.company.companyId = :companyId AND jp.jobPostingId <> :jobPostingId")
    List<Long> findAllJobPostingIdsByCompanyId(@Param("companyId") Long companyId, @Param("jobPostingId") Long jobPostingId);


}
