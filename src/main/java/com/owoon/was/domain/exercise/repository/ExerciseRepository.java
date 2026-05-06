package com.owoon.was.domain.exercise.repository;

import com.owoon.was.domain.exercise.entity.Exercise;
import com.owoon.was.domain.exercise.entity.enums.ExerciseCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 운동 종류 조회 및 저장을 담당하는 repository.
 */
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    /**
     * 등록 순서대로 운동 종류 목록을 조회한다.
     */
    List<Exercise> findAllByOrderByIdAsc();

    /**
     * 운동 코드로 운동 종류를 조회한다.
     */
    Optional<Exercise> findByCode(ExerciseCode code);

    /**
     * 운동 코드 중복 여부를 확인한다.
     */
    boolean existsByCode(ExerciseCode code);
}
