package com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingReadDetailResponseDto {
    private Long jobPostingId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String description;
    private String skill;
    private List<Long> otherJobPostings;
    private LocalDateTime createdAt;
}
