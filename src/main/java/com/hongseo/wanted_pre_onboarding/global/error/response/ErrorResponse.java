package com.hongseo.wanted_pre_onboarding.global.error.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

/**
 * API 에러 응답을 표준화하여 클라이언트에 제공하는 클래스
 * - API 에러 시 응답의 구조를 정의
 * - 각 API 요청에 대한 오류 상세
 *
 * @param code 에러 코드
 * @param message 사용자에게 보여질 에러 메시자
 * @param errors 발생한 에러의 상세한 필드 오류 리스트
 */


@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    /**
     * 필드별 오류 정보를 담는 내부 클래스
     * 이 클래스는 각각의 유효성 검사 실패에 대한 필드명과 실패 이유를 클라이언트에 제공
     *
     * @param field 오류가 발생한 필드명
     * @param message 오류 메시지, 주로 유효성 검사 실패의 구체적인 이유
     */
    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {

        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}