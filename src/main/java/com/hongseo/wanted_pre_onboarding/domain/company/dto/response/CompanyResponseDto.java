package com.hongseo.wanted_pre_onboarding.domain.company.dto.response;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDto{
    private Long companyId;
    private String companyName;
    private String country;
    private String region;
    private LocalDateTime createdAt;
}

