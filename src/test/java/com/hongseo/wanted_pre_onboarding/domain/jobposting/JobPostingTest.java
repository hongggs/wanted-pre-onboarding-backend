package com.hongseo.wanted_pre_onboarding.domain.jobposting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트 메서드 실행 순서를 지정
public class JobPostingTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    private static final String BASE_URL = "/api/job-posting";
    private static Long savedJobPostingId; // 저장된 채용공고 ID

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @Order(1)
    @DisplayName("채용 공고 정상 등록")
    void createJobPosting() throws Exception {
        JobPostingCreateRequestDto requestDto = new JobPostingCreateRequestDto(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자를 뽑습니다.", "Java, Spring Boot");
        savedJobPostingId = performPostRequest(requestDto, BASE_URL, HttpStatus.CREATED);
        JobPostingReadDetailResponseDto saved = performGetRequest(BASE_URL + "/" + savedJobPostingId);
        assertEquals(requestDto.getPosition(), saved.getPosition());
        assertEquals(requestDto.getReward(), saved.getReward());
        assertEquals(requestDto.getDescription(), saved.getDescription());
        assertEquals(requestDto.getSkill(), saved.getSkill());
    }

    @Test
    @Order(2)
    @DisplayName("등록한 채용 공고 정상 수정")
    void updateJobPosting() throws Exception {
        JobPostingUpdateRequestDto requestDto = new JobPostingUpdateRequestDto("백엔드 개발자", 2000000, "성실한 백엔드 개발자 뽑습니다.", "Kotlin, Spring");
        performPutRequest(requestDto, BASE_URL + "/" + savedJobPostingId);
        JobPostingReadDetailResponseDto updated = performGetRequest(BASE_URL + "/" + savedJobPostingId);
        assertEquals(requestDto.getPosition(), updated.getPosition());
        assertEquals(requestDto.getReward(), updated.getReward());
        assertEquals(requestDto.getDescription(), updated.getDescription());
        assertEquals(requestDto.getSkill(), updated.getSkill());
    }

    @Test
    @Order(3)
    @DisplayName("등록한 채용 공고 정상 삭제")
    void deleteJobPosting() throws Exception {
        performDeleteRequest(BASE_URL + "/" + savedJobPostingId);
         mockMvc.perform(get(BASE_URL + "/" + savedJobPostingId))
                .andExpect(status().isBadRequest());
    }

    private Long performPostRequest(Object dto, String url, HttpStatus expectedStatus) throws Exception {
        MvcResult result = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().is(expectedStatus.value()))
                .andReturn();
        return Long.parseLong(result.getResponse().getContentAsString());
    }

    private void performPutRequest(Object dto, String url) throws Exception {
        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    private void performDeleteRequest(String url) throws Exception {
        mockMvc.perform(delete(url)).andExpect(status().isNoContent());
    }

    private JobPostingReadDetailResponseDto performGetRequest(String url) throws Exception {
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();
        return mapper.readValue(result.getResponse().getContentAsString(), JobPostingReadDetailResponseDto.class);
    }
}