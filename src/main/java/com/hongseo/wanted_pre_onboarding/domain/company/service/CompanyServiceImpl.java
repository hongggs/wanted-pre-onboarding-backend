package com.hongseo.wanted_pre_onboarding.domain.company.service;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.model.adapter.CompanyAndDtoAdapter;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyDto) {
        Company company = CompanyAndDtoAdapter.dtoToEntity(companyDto);
        Company savedCompany = companyRepository.save(company);
        return CompanyAndDtoAdapter.entityToDto(savedCompany);
    }
}
