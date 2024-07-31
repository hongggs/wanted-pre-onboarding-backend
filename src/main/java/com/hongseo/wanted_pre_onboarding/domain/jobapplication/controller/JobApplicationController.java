package com.hongseo.wanted_pre_onboarding.domain.jobapplication.controller;

import com.hongseo.wanted_pre_onboarding.domain.jobapplication.dto.request.JobApplicationRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 지원 관련 HTTP 요청 처리하는 컨트롤러
 * 각 메소드는 RESTful API를 통해 기능을 제공
 */
@RestController
@RequestMapping("/api/job-application")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    /**
     * 사용자의 채용 지원 요청을 받아 처리
     * @param jobApplicationDto jobApplicationDto 채용 지원에 필요한 정보를 담은 DTO
     * @return ResponseEntity를 통해 생성된 지원 결과와 HTTP 상태 코드를 반환, 성공적인 지원의 경우 HTTP 상태 코드 201 (CREATED)를 반환
     */
    @PostMapping
    public ResponseEntity<?> apply(@RequestBody JobApplicationRequestDto jobApplicationDto) {
        return new ResponseEntity<>(jobApplicationService.apply(jobApplicationDto), HttpStatus.CREATED);
    }
}
