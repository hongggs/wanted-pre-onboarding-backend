package com.hongseo.wanted_pre_onboarding.domain.company.model.adapter;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;

public class CompanyAndDtoAdapter {
    public static Company dtoToEntity(CompanyRequestDto companyDto) {
        return Company.builder()
                .companyName(companyDto.getCompanyName())
                .country(companyDto.getCountry())
                .region(companyDto.getRegion())
                .build();
    }


    public static CompanyResponseDto entityToDto(Company company) {
        return CompanyResponseDto.builder()
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .country(company.getCountry())
                .region(company.getRegion())
                .createdAt(company.getCreatedAt())
                .build();
    }
}
