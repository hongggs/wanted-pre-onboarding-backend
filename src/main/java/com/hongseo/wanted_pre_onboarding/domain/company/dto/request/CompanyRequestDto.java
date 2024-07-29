package com.hongseo.wanted_pre_onboarding.domain.company.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyRequestDto {
    @NotBlank(message = "회사명을 작성해주세요.")
    private String companyName;
    @NotBlank(message = "국가를 작성해주세요.")
    private String country;
    @NotBlank(message = "지역을 작성해주세요.")
    private String region;
}
