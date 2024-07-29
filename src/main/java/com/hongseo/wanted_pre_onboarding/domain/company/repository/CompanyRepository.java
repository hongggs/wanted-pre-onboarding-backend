package com.hongseo.wanted_pre_onboarding.domain.company.repository;

import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
