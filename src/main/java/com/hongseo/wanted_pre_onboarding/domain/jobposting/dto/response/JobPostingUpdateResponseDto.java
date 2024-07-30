package com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingUpdateResponseDto {
    private Long jobPostingId;
    private String position;
    private Integer reward;
    private String description;
    private String skill;
}
