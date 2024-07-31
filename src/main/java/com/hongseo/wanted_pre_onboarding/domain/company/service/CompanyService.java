package com.hongseo.wanted_pre_onboarding.domain.company.service;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import java.util.Optional;

/**
 * 회사 관련 비즈니스 로직을 정의하는 서비스 인터페이스
 */
public interface CompanyService {
    public CompanyResponseDto createCompany(CompanyRequestDto companyDto);
    CompanyResponseDto readCompany(Long companyId);
}
