package com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response;

import java.time.LocalDateTime;

public class JobPostingResponseDto {
    private Long jobPostingId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String description;
    private String skill;
    private LocalDateTime createdAt;
}
