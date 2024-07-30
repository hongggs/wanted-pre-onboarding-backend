package com.hongseo.wanted_pre_onboarding.domain.jobposting.controller;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.service.JobPostingService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-posting")
public class JobPostingController {
    @Autowired
    private JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<?> createJobPosting(@Valid @RequestBody JobPostingCreateRequestDto jobPostingDto) {
        return new ResponseEntity<>(jobPostingService.createJobPosting(jobPostingDto), HttpStatus.CREATED);
    }

    @PutMapping("/{jobPostingId}")
    public ResponseEntity<?> updateJobPosting(@PathVariable Long jobPostingId, @Valid @RequestBody JobPostingUpdateRequestDto jobPostingDto) {
        return new ResponseEntity<>(jobPostingService.updateJobPosting(jobPostingId, jobPostingDto), HttpStatus.OK);
    }

    @DeleteMapping("/{jobPostingId}")
    public ResponseEntity<?> deleteJobPosting(@PathVariable Long jobPostingId) {
        jobPostingService.deleteJobPosting(jobPostingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<JobPostingReadResponseDto>> getAllJobPostings() {
        return new ResponseEntity<>(jobPostingService.getAllJobPostings(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPostingReadResponseDto>> searchJobPostings(@RequestParam String keyword) {
        return new ResponseEntity<>(jobPostingService.searchJobPostingsByKeyword(keyword), HttpStatus.OK);
    }
}
