package com.hongseo.wanted_pre_onboarding.domain.jobposting.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.service.JobPostingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class JobPostingControllerTest {

    @Mock
    private JobPostingService jobPostingService;

    @InjectMocks
    private JobPostingController jobPostingController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화 추가
        mockMvc = MockMvcBuilders.standaloneSetup(jobPostingController).build();
    }

    @Test
    @DisplayName("채용 공고 정상 등록")
    void createJobPosting_ReturnsCreated() throws Exception {
        //Given
        JobPostingRequestDto dto = new JobPostingRequestDto(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자를 뽑습니다.", "Java, Spring Boot");
        when(jobPostingService.createJobPosting(any(JobPostingRequestDto.class))).thenReturn(1L);

        //When, Then
        mockMvc.perform(post("/api/job-posting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("채용 공고에 position에 null 값 설정 시 예외 발생")
    void createJobPosting_WhenParamsAreInvalid_Null() throws Exception {
        //Given
        JobPostingRequestDto dto = new JobPostingRequestDto(1L, "", 100000, "성실한 프론트엔드 개발자 뽑습니다.", "React");

        //When, Then
        mockMvc.perform(post("/api/job-posting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("채용 공고에 reward에 0이하의 값 설정 시 예외 발생")
    void createJobPosting_WhenParamsAreInvalid_Min() throws Exception {
        // Given
        JobPostingRequestDto dto = new JobPostingRequestDto(1L, "", 100000, "성실한 프론트엔드 개발자 뽑습니다.", "React");

        //When, Then
        mockMvc.perform(post("/api/job-posting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}