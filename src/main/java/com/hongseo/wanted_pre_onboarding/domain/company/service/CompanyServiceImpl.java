package com.hongseo.wanted_pre_onboarding.domain.company.service;

import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.error.exception.CompanyNotFoundException;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.model.adapter.CompanyAndDtoAdapter;
import com.hongseo.wanted_pre_onboarding.domain.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회사 관련 서비스를 제공하는 클래스
 */
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * 새로운 회사를 생성
     * @param companyDto 회사 생성을 위해 필요한 정보를 담은 DTO
     * @return 생성된 회사 정조
     */
    @Override
    @Transactional
    public CompanyResponseDto createCompany(CompanyRequestDto companyDto) {
        Company company = CompanyAndDtoAdapter.dtoToEntity(companyDto);
        Company savedCompany = companyRepository.save(company);
        return CompanyAndDtoAdapter.entityToDto(savedCompany);
    }

    /**
     * 지정된 ID를 가진 회사의 정보를 조회
     * @param companyId 조회할 회사의 ID
     * @return 조회된 회사 정보
     */
    @Override
    public CompanyResponseDto readCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        return CompanyAndDtoAdapter.entityToDto(company);
    }
}
