package com.hongseo.wanted_pre_onboarding.global.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * 모든 엔티티의 생성 시간과 수정 시간을 자동으로 관리하기 위한 베이스 클래스
 */
@Getter
@MappedSuperclass// JPA 엔티티 클래스들이 이 클래스를 상속할 경우 필드들을 컬럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)// JPA 엔티티 감사를 위해 리스너 추가
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
