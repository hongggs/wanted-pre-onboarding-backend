package com.hongseo.wanted_pre_onboarding.domain.jobapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplicationRequestDto {
    @NotNull(message = "채용공고 id를 작성해주세요.")
    Long jobPostingId;
    @NotNull(message = "사용자 id를 작성해주세요.")
    Long userId;
}
