package com.owoon.was.domain.routine.service;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import com.owoon.was.domain.exercise.entity.Exercise;
import com.owoon.was.domain.exercise.repository.ExerciseRepository;
import com.owoon.was.domain.routine.dto.request.RoutineCreateRequest;
import com.owoon.was.domain.routine.dto.request.RoutineExerciseCreateRequest;
import com.owoon.was.domain.routine.dto.response.RoutineResponse;
import com.owoon.was.domain.routine.dto.response.RoutineSummaryResponse;
import com.owoon.was.domain.routine.entity.Routine;
import com.owoon.was.domain.routine.entity.RoutineExercise;
import com.owoon.was.domain.routine.repository.RoutineRepository;
import com.owoon.was.domain.user.entity.User;
import com.owoon.was.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    /**
     * 회원이 수행할 운동 목록과 목표치를 묶어 루틴을 생성한다.
     */
    @Transactional
    public RoutineResponse createRoutine(Long userId, RoutineCreateRequest request) {
        User user = findUser(userId);
        validateExerciseOrders(request.exercises());

        Routine routine = Routine.builder()
                .user(user)
                .name(request.name())
                .description(request.description())
                .build();

        request.exercises().forEach(exerciseRequest -> routine.addRoutineExercise(
                RoutineExercise.builder()
                        .exercise(findExercise(exerciseRequest.exerciseId()))
                        .exerciseOrder(exerciseRequest.exerciseOrder())
                        .targetReps(exerciseRequest.targetReps())
                        .targetSets(exerciseRequest.targetSets())
                        .restSeconds(exerciseRequest.restSeconds())
                        .build()
        ));

        return RoutineResponse.from(routineRepository.save(routine));
    }

    /**
     * 회원 ID로 루틴 목록을 조회한다.
     */
    public List<RoutineSummaryResponse> getRoutines(Long userId) {
        validateUserExists(userId);

        return routineRepository.findAllByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(RoutineSummaryResponse::from)
                .toList();
    }

    /**
     * 회원 ID와 루틴 ID로 루틴 상세 정보를 조회한다.
     */
    public RoutineResponse getRoutine(Long userId, Long routineId) {
        validateUserExists(userId);

        return RoutineResponse.from(findRoutine(userId, routineId));
    }

    /**
     * 회원 ID와 루틴 ID로 루틴을 삭제한다.
     */
    @Transactional
    public void deleteRoutine(Long userId, Long routineId) {
        validateUserExists(userId);
        routineRepository.delete(findRoutine(userId, routineId));
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    private Exercise findExercise(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXERCISE_NOT_FOUND));
    }

    private Routine findRoutine(Long userId, Long routineId) {
        return routineRepository.findByIdAndUserId(routineId, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
    }

    private void validateExerciseOrders(List<RoutineExerciseCreateRequest> exercises) {
        Set<Integer> orders = new HashSet<>();
        boolean hasDuplicateOrder = exercises.stream()
                .map(RoutineExerciseCreateRequest::exerciseOrder)
                .anyMatch(order -> !orders.add(order));

        if (hasDuplicateOrder) {
            throw new CustomException(ErrorCode.DUPLICATE_ROUTINE_EXERCISE_ORDER);
        }
    }
}
