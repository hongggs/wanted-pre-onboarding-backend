package com.hongseo.wanted_pre_onboarding.domain.company.model.adapter;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;

/**
 * Company 엔티티와 관련 DTO 간의 데이터 변환을 처리하는 어댑터 클래스
 */
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
