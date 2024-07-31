package com.hongseo.wanted_pre_onboarding.domain.company.model;

import com.hongseo.wanted_pre_onboarding.global.base.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회사 엔티티 클래스
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId; //회사 id
    @NotNull
    private String companyName; // 회사명
    @NotNull
    private String country; // 국가
    @NotNull
    private String region; // 지역
}
