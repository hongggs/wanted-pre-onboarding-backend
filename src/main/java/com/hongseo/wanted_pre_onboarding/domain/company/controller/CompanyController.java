package com.hongseo.wanted_pre_onboarding.domain.company.controller;


import com.hongseo.wanted_pre_onboarding.domain.company.dto.request.CompanyRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.company.dto.response.CompanyResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.company.model.Company;
import com.hongseo.wanted_pre_onboarding.domain.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회사 관련 HTTP 요청 처리하는 컨트롤러
 * 각 메소드는 RESTful API를 통해 기능을 제공
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 새로운 회사를 생성
     * @param companyDto 회사 생성을 위해 필요한 정보를 담은 DTO
     * @return 생성된 회사의 정보와 HTTP 상태 코드 201(Created)
     */
    @PostMapping
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyRequestDto companyDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createCompany(companyDto));
    }

    /**
     * 지정된 ID를 가진 회사의 정보를 조회
     * @param companyId 조회할 회사의 ID
     * @return 조회된 회사의 정보와 HTTP 상태 코드 200(OK)
     */
    @GetMapping("/{companyId}")
    public ResponseEntity<?> readCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.readCompany(companyId));
    }
}
