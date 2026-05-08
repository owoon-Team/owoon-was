package com.owoon.was.domain.routine.repository;

import com.owoon.was.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 회원별 루틴 조회 및 저장을 담당하는 repository.
 */
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    /**
     * 회원 ID로 루틴 목록을 최신 생성순으로 조회한다.
     */
    List<Routine> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    /**
     * 회원 ID와 루틴 ID로 루틴을 조회한다.
     */
    @EntityGraph(attributePaths = {"routineExercises", "routineExercises.exercise"})
    Optional<Routine> findByIdAndUserId(Long routineId, Long userId);
}
