package com.owoon.was.domain.user.repository;

import com.owoon.was.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 신체 정보 조회 및 저장을 담당하는 repository.
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    /**
     * 회원 ID로 신체 정보를 조회한다.
     */
    Optional<UserProfile> findByUserId(Long userId);

    /**
     * 회원의 신체 정보 등록 여부를 확인한다.
     */
    boolean existsByUserId(Long userId);
}
