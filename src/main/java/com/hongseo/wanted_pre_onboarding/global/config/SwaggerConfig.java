package com.hongseo.wanted_pre_onboarding.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("원티드 프리온보딩 백엔드 인턴십 선발과제")
                .description("본 서비스는 기업의 채용을 위한 웹 서비스 입니다. ")
                .version("1.0.0");
    }
}

