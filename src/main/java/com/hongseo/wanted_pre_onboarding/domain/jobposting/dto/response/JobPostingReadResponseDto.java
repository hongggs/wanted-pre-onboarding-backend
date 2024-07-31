package com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채용공고 읽기 응답 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingReadResponseDto {
    private Long jobPostingId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String skill;
    private LocalDateTime createdAt;
}
