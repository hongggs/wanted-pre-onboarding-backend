package com.hongseo.wanted_pre_onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //BaseTimeEntity를 자동으로 관리
public class WantedPreOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WantedPreOnboardingApplication.class, args);
	}
}
