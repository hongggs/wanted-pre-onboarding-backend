package com.hongseo.wanted_pre_onboarding.domain.user.repository;

import com.hongseo.wanted_pre_onboarding.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
