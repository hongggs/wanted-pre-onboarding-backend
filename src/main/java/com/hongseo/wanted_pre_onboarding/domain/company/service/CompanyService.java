package com.hongseo.wanted_pre_onboarding.domain.company.service;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import java.util.Optional;

public interface CompanyService {
    public CompanyResponseDto createCompany(CompanyRequestDto companyDto);
    CompanyResponseDto readCompany(Long companyId);
}
