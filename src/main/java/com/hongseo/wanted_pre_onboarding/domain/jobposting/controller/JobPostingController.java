package com.hongseo.wanted_pre_onboarding.domain.jobposting.controller;

import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.service.JobPostingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * 채용공고 관련 HTTP 요청 처리하는 컨트롤러
 * 각 메소드는 RESTful API를 통해 기능을 제공
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/job-posting")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    /**
     * 새로운 채용공고 생성
     * @param jobPostingDto 채용공고 생성에 필요한 데이터를 담은 DTO
     * @return 생성된 채용공고의 ID를 포함하는 ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> createJobPosting(@Valid @RequestBody JobPostingCreateRequestDto jobPostingDto) {
        return new ResponseEntity<>(jobPostingService.createJobPosting(jobPostingDto), HttpStatus.CREATED);
    }

    /**
     * 주어진 ID의 채용공고 업데이트
     * @param jobPostingId 업데이트할 채용공고의 ID
     * @param jobPostingDto 업데이트에 필요한 데이터를 담은 DTO
     * @return 업데이트된 채용공고의 상세 정보를 포함하는 ResponseEntity
     */
    @PutMapping("/{jobPostingId}")
    public ResponseEntity<?> updateJobPosting(@PathVariable Long jobPostingId, @Valid @RequestBody JobPostingUpdateRequestDto jobPostingDto) {
        return new ResponseEntity<>(jobPostingService.updateJobPosting(jobPostingId, jobPostingDto), HttpStatus.OK);
    }

    /**
     * 주어진 ID의 채용공고 삭제
     * @param jobPostingId 삭제할 채용공고의 ID
     * @return HTTP 상태 NO_CONTENT를 반환하는 ResponseEntity
     */
    @DeleteMapping("/{jobPostingId}")
    public ResponseEntity<?> deleteJobPosting(@PathVariable Long jobPostingId) {
        jobPostingService.deleteJobPosting(jobPostingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 모든 채용공고 조회
     * @return 채용공고 목록을 반환하는 ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<JobPostingReadResponseDto>> getAllJobPostings() {
        return new ResponseEntity<>(jobPostingService.getAllJobPostings(), HttpStatus.OK);
    }

    /**
     * 키워드로 채용공고 검색
     * @param keyword 검색 키워드
     * @return 검색된 채용공고 목록을 반환하는 ResponseEntity
     */
    @GetMapping("/search")
    public ResponseEntity<List<JobPostingReadResponseDto>> searchJobPostings(@RequestParam String keyword) {
        return new ResponseEntity<>(jobPostingService.searchJobPostingsByKeyword(keyword), HttpStatus.OK);
    }

    /**
     * 주어진 ID의 채용공고 상세 정보를 조회
     * @param jobPostingId 상세 정보를 조회할 채용공고의 ID
     * @return 채용공고의 상세 정보를 반환하는 JobPostingReadDetailResponseDto
     */
    @GetMapping("/{jobPostingId}")
    public JobPostingReadDetailResponseDto getJobPostingDetails(@PathVariable Long jobPostingId) {
        return jobPostingService.getJobPostingDetail(jobPostingId);
    }
}
