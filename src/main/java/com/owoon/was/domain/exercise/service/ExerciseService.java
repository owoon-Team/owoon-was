package com.owoon.was.domain.exercise.service;

import com.owoon.was.domain.exercise.dto.response.ExerciseResponse;
import com.owoon.was.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    /**
     * 루틴 생성 시 선택할 수 있는 운동 종류 목록을 조회한다.
     */
    public List<ExerciseResponse> getExercises() {
        return exerciseRepository.findAllByOrderByIdAsc().stream()
                .map(ExerciseResponse::from)
                .toList();
    }
}
