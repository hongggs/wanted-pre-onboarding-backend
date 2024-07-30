package com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingCreateRequestDto {
    @NotNull(message = "회사번호를 작성해주세요.")
    private Long companyId;
    @NotBlank(message = "채용포지션을 작성해주세요.")
    private String position;
    @NotNull(message = "채용보상금을 작성해주세요.")
    @Min(value = 0, message = "채용보상금은 0 이상이어야 합니다.")
    @Max(value = 10000000, message = "채용보상금은 10,000,000 이하여야 합니다.")
    private Integer reward;
    @NotBlank(message = "채용내용을 작성해주세요.")
    private String description;
    @NotBlank(message = "사용기술을 작성해주세요.")
    private String skill;
}
