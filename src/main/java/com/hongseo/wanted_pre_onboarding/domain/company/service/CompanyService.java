package com.hongseo.wanted_pre_onboarding.domain.company.service;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;

public interface CompanyService {
    public CompanyResponseDto createCompany(CompanyRequestDto companyDto);
}
