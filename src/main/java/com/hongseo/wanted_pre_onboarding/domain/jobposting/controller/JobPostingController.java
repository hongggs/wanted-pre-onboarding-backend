package com.hongseo.wanted_pre_onboarding.domain.jobposting.controller;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.service.JobPostingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-posting")
public class JobPostingController {
    @Autowired
    private JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<?> createJobPosting(@Valid @RequestBody JobPostingRequestDto jobPostingDto) {
        return new ResponseEntity<>(jobPostingService.createJobPosting(jobPostingDto), HttpStatus.CREATED);
    }
}
