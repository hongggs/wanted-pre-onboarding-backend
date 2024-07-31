package com.hongseo.wanted_pre_onboarding.domain.jobposting.repository;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.model.JobPosting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 채용공고 데이터에 접근 제공하는 JPA 리포지토리 인터페이스
 */
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    /**
     * 모든 채용공고를 회사 정보와 함께 조회하여 반환
     * 쿼리는 회사 정보를 즉시 로딩(fetch)하며, 생성 시간의 내림차순으로 정렬
     * @return 회사 정보가 포함된 채용공고 목록
     */
    @Query("SELECT jp FROM JobPosting jp JOIN FETCH jp.company ORDER BY jp.createdAt DESC")
    List<JobPosting> findAllWithCompany();

    /**
     * 주어진 키워드를 포함하는 채용공고를 조회
     * 키워드는 채용공고의 포지션, 설명, 기술 + 회사의 이름, 국가, 지역에서 검색
     * 결과는 생성 시간의 내림차순으로 정렬
     * @param keyword 검색 키워드
     * @return 키워드를 포함하는 채용공고 목록
     */
    @Query("SELECT jp FROM JobPosting jp JOIN jp.company c WHERE " +
            "LOWER(c.companyName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.country) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.region) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(jp.position) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(jp.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(jp.skill) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY jp.createdAt DESC")
    List<JobPosting> findByKeyword(@Param("keyword") String keyword);

    /**
     * 특정 회사에 속하면서 지정된 채용공고 ID를 제외한 모든 채용공고의 ID를 조회
     * @param companyId 회사 ID
     * @param jobPostingId 제외할 채용공고 ID
     * @return 해당 조건을 만족하는 채용공고 ID 목록
     */
    @Query("SELECT jp.jobPostingId FROM JobPosting jp WHERE jp.company.companyId = :companyId AND jp.jobPostingId <> :jobPostingId")
    List<Long> findAllJobPostingIdsByCompanyId(@Param("companyId") Long companyId, @Param("jobPostingId") Long jobPostingId);


}
