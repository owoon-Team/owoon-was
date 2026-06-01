package com.owoon.was.domain.routineexerciseresult.repository;

import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 회원별 루틴 운동 실행 결과 조회를 담당하는 repository.
 */
public interface RoutineExerciseResultRepository extends JpaRepository<RoutineExerciseResult, Long> {

    /**
     * 회원 ID와 루틴 실행 기록 ID로 운동별 실행 결과 목록을 조회한다.
     */
    @EntityGraph(attributePaths = {"routineExercise", "routineExercise.exercise"})
    List<RoutineExerciseResult> findAllByRoutineSessionIdAndRoutineSessionUserIdOrderByExerciseOrderSnapshotAsc(
            Long sessionId,
            Long userId
    );

    /**
     * 회원 ID, 루틴 실행 기록 ID, 운동별 실행 결과 ID로 운동별 실행 결과를 조회한다.
     */
    @EntityGraph(attributePaths = {"routineExercise", "routineExercise.exercise"})
    Optional<RoutineExerciseResult> findByIdAndRoutineSessionIdAndRoutineSessionUserId(
            Long resultId,
            Long sessionId,
            Long userId
    );

    /**
     * 회원 ID와 루틴 실행 기록 ID, 운동별 실행 결과 ID로 운동별 실행 결과 존재 여부를 확인한다.
     */
    boolean existsByIdAndRoutineSessionIdAndRoutineSessionUserId(
            Long resultId,
            Long sessionId,
            Long userId
    );
}
