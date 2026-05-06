package com.owoon.was.domain.user.repository;

import com.owoon.was.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 기본 정보 조회 및 저장을 담당하는 repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 회원을 조회한다.
     */
    Optional<User> findByEmail(String email);

    /**
     * 이메일 중복 여부를 확인한다.
     */
    boolean existsByEmail(String email);
}
