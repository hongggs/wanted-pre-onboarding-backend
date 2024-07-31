package com.hongseo.wanted_pre_onboarding.domain.jobposting;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hongseo.wanted_pre_onboarding.domain.jobapplication.dto.request.JobApplicationRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingCreateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.request.JobPostingUpdateRequestDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadDetailResponseDto;
import com.hongseo.wanted_pre_onboarding.domain.jobposting.dto.response.JobPostingReadResponseDto;
import java.util.List;
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

/**
 * JobPosting(채용공고) 전체 읽기, 아이디로 읽기, 저장, 수정, 삭제, 검색이 정상 작동하는지 확인
 */
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
    @DisplayName("채용 공고 2개 정상 등록")
    void createJobPosting() throws Exception {
        //Given - 채용공고 dto 2개 생성
        JobPostingCreateRequestDto dto1 = new JobPostingCreateRequestDto(1L, "백엔드 개발자", 1000000, "성실한 백엔드 개발자를 뽑습니다.", "Java, Spring Boot");
        JobPostingCreateRequestDto dto2 = new JobPostingCreateRequestDto(1L, "프론트엔드 개발자", 2000000, "성실한 프론트엔드 개발자를 뽑습니다.", "React.js");

        //When - 채용공고 2개 저장
        savedJobPostingId = performPostRequest(dto1, BASE_URL, HttpStatus.CREATED);
        performPostRequest(dto2, BASE_URL, HttpStatus.CREATED);

        //Then - 첫번째로 생성한 채용공고 dto1과 읽어온 savedJobPostingId의 데이터와 내용이 같음을 확인
        JobPostingReadDetailResponseDto saved = performGetRequest(BASE_URL + "/" + savedJobPostingId);
        assertEquals(dto1.getPosition(), saved.getPosition());
        assertEquals(dto1.getReward(), saved.getReward());
        assertEquals(dto1.getDescription(), saved.getDescription());
        assertEquals(dto1.getSkill(), saved.getSkill());
    }

    @Test
    @Order(2)
    @DisplayName("전체 채용 공고를 읽어와 두 개인지 확인")
    void readAllJobPosting() throws Exception {
        // Given - 앞서 2개의 채용공고를 저장했음

        // When - 모든 채용공고를 조회
        MvcResult result = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        // Then - 전체 채용공고 개수가 2개인지 확인
        List<JobPostingReadResponseDto> jobPostings = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<JobPostingReadResponseDto>>() {});
        assertEquals(jobPostings.size(), 2);
    }

    @Test
    @Order(3)
    @DisplayName("채용 공고 정상 검색")
    void searchByKeyword() throws Exception {
        // Given - 앞서 2개의 채용공고를 저장했음
        // When - "백엔드" 키워드를 이용해 검색(result1), "성실한" 키워드를 이용해 검색(result2)
        MvcResult result1 = mockMvc.perform(get(BASE_URL + "/search?keyword=백엔드"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = mockMvc.perform(get(BASE_URL + "/search?keyword=성실한"))
                .andExpect(status().isOk())
                .andReturn();

        // Then - 개수 검증
        List<JobPostingReadResponseDto> jobPostings1 = mapper.readValue(result1.getResponse().getContentAsString(), new TypeReference<List<JobPostingReadResponseDto>>() {});
        assertEquals(jobPostings1.size(), 1); //백엔드 키워드 포함한 채용공고 개수 1개

        List<JobPostingReadResponseDto> jobPostings2 = mapper.readValue(result2.getResponse().getContentAsString(), new TypeReference<List<JobPostingReadResponseDto>>() {});
        assertEquals(jobPostings2.size(), 2); //성실한 키워드 포함한 채용공고 개수 2개
    }

    @Test
    @Order(4)
    @DisplayName("등록한 채용 공고 정상 지원")
    void apply() throws Exception {
        // Given - 미리 삽입해둔 사용자 아이디 1과 위에서 저장한 채용공고 아이디를 활용해서 공고 지원 dto 생성
        JobApplicationRequestDto dto = new JobApplicationRequestDto(1L, 1L);

        // When - JobApplication 데이터 저장
        // Then - 정상 저장 상태 코드 확인
        mockMvc.perform(post("/api/job-application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(5)
    @DisplayName("등록한 채용 공고 중복 지원 시 오류 발생")
    void apply_dup() throws Exception {
        // Given - 미리 삽입해둔 사용자 아이디 1과 위에서 저장한 채용공고 아이디를 활용해서 공고 지원 dto 생성
        JobApplicationRequestDto dto = new JobApplicationRequestDto(1L, 1L);

        // When - JobApplication 데이터 저장
        // Then - 이미 저장된 기록이 있으므로 정상 저장되지 않음
        mockMvc.perform(post("/api/job-application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(6)
    @DisplayName("등록한 채용 공고 정상 수정")
    void updateJobPosting() throws Exception {
        //Given - 수정할 dto 생성
        JobPostingUpdateRequestDto requestDto = new JobPostingUpdateRequestDto("백엔드 개발자", 2000000, "성실한 백엔드 개발자 뽑습니다.", "Kotlin, Spring");

        //When - 해당 데이터 수정
        performPutRequest(requestDto, BASE_URL + "/" + savedJobPostingId);

        //Then - 수정한 내용 반영 확안
        JobPostingReadDetailResponseDto updated = performGetRequest(BASE_URL + "/" + savedJobPostingId);
        assertEquals(requestDto.getPosition(), updated.getPosition());
        assertEquals(requestDto.getReward(), updated.getReward());
        assertEquals(requestDto.getDescription(), updated.getDescription());
        assertEquals(requestDto.getSkill(), updated.getSkill());
    }

    @Test
    @Order(7)
    @DisplayName("등록한 채용 공고 정상 삭제")
    void deleteJobPosting() throws Exception {
        // Given - 저장된 데이터 존재
        // When - 해당 데이터 삭제
        performDeleteRequest(BASE_URL + "/" + savedJobPostingId);

        // Then - 해당 아이디로 데이터 찾을 경우 찾을 수 없음
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