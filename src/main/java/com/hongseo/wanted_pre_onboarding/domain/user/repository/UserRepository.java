package com.hongseo.wanted_pre_onboarding.domain.user.repository;

/**
 * 사용자 데이터에 접근 제공하는 JPA 리포지토리 인터페이스
 */
import com.hongseo.wanted_pre_onboarding.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
