package com.hongseo.wanted_pre_onboarding.domain.company.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponseDto {
    private Long companyId;
    private String companyName;
    private String country;
    private String region;
}

