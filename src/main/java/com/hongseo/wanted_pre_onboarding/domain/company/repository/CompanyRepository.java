package com.hongseo.wanted_pre_onboarding.domain.company.repository;

import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 회사 데이터에 접근 제공하는 JPA 리포지토리 인터페이스
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
