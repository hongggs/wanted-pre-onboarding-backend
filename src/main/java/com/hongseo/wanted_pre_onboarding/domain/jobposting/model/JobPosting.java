package com.hongseo.wanted_pre_onboarding.domain.jobposting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.model.JobApplication;
import com.hongseo.wanted_pre_onboarding.global.base.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosting extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobPostingId;
    @NotNull
    private String position;
    @NotNull
    private Integer reward;
    @NotNull
    private String description;
    @NotNull
    private String skill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;
}
