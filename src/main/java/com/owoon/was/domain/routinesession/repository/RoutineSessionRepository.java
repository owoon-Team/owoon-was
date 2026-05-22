package com.owoon.was.domain.routinesession.repository;

import com.owoon.was.domain.routinesession.entity.RoutineSession;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 회원별 루틴 실행 기록 조회 및 저장을 담당하는 repository.
 */
public interface RoutineSessionRepository extends JpaRepository<RoutineSession, Long> {

    /**
     * 회원 ID로 루틴 실행 기록 목록을 최신 시작순으로 조회한다.
     */
    @EntityGraph(attributePaths = {"routine"})
    List<RoutineSession> findAllByUserIdOrderByStartedAtDesc(Long userId);

    /**
     * 회원 ID와 루틴 실행 기록 ID로 루틴 실행 기록을 조회한다.
     */
    @EntityGraph(attributePaths = {
            "routine",
            "routineExerciseResults",
            "routineExerciseResults.routineExercise",
            "routineExerciseResults.routineExercise.exercise"
    })
    Optional<RoutineSession> findByIdAndUserId(Long sessionId, Long userId);
}
