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
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        User routineOwner = findRoutineOwner(userId);
        validateExerciseOrders(request.exercises());

        Routine routine = Routine.builder()
                .user(routineOwner)
                .name(request.name())
                .description(request.description())
                .build();

        request.exercises().forEach(exerciseRequest ->
                routine.addRoutineExercise(createRoutineExercise(exerciseRequest))
        );

        return RoutineResponse.from(routineRepository.save(routine));
    }

    /**
     * 회원 ID와 루틴 ID로 루틴 기본 정보와 포함 운동 목록을 수정한다.
     */
    @Transactional
    public RoutineResponse updateRoutine(Long userId, Long routineId, RoutineCreateRequest request) {
        validateExerciseOrders(request.exercises());
        validateRoutineExerciseIds(request.exercises());

        Routine routine = findRoutine(userId, routineId);
        routine.update(request.name(), request.description());
        syncRoutineExercises(routine, request.exercises());

        return RoutineResponse.from(routine);
    }

    /**
     * 회원 ID로 루틴 목록을 조회한다.
     */
    public List<RoutineSummaryResponse> getRoutines(Long userId) {
        validateRoutineOwnerExists(userId);

        return routineRepository.findAllByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(userId).stream()
                .map(RoutineSummaryResponse::from)
                .toList();
    }

    /**
     * 회원 ID와 루틴 ID로 루틴 상세 정보를 조회한다.
     */
    public RoutineResponse getRoutine(Long userId, Long routineId) {
        return RoutineResponse.from(findRoutine(userId, routineId));
    }

    /**
     * 회원 ID와 루틴 ID로 루틴을 삭제한다.
     */
    @Transactional
    public void deleteRoutine(Long userId, Long routineId) {
        findRoutine(userId, routineId).delete();
    }

    /**
     * 루틴 소유자로 사용할 회원을 조회한다.
     */
    private User findRoutineOwner(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * 루틴 목록 조회 전 소유자 회원 존재 여부를 확인한다.
     */
    private void validateRoutineOwnerExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    /**
     * 운동 ID로 운동 종류를 조회한다.
     */
    private Exercise findExercise(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXERCISE_NOT_FOUND));
    }

    /**
     * 회원 ID와 루틴 ID로 루틴을 조회한다.
     */
    private Routine findRoutine(Long userId, Long routineId) {
        return routineRepository.findByIdAndUserIdAndDeletedAtIsNull(routineId, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
    }

    /**
     * 기존 운동 종류를 루틴에 연결할 설정 row를 생성한다.
     */
    private RoutineExercise createRoutineExercise(RoutineExerciseCreateRequest exerciseRequest) {
        return RoutineExercise.builder()
                .exercise(findExercise(exerciseRequest.exerciseId()))
                .exerciseOrder(exerciseRequest.exerciseOrder())
                .targetReps(exerciseRequest.targetReps())
                .targetSets(exerciseRequest.targetSets())
                .restSeconds(exerciseRequest.restSeconds())
                .build();
    }

    /**
     * 기존 루틴 운동 row를 보존하면서 요청값 기준으로 update, insert, soft delete를 동기화한다.
     */
    private void syncRoutineExercises(Routine routine, List<RoutineExerciseCreateRequest> exerciseRequests) {
        Map<Long, RoutineExercise> currentRoutineExercises = routine.getRoutineExercises().stream()
                .filter(routineExercise -> !routineExercise.isDeleted())
                .collect(Collectors.toMap(RoutineExercise::getId, Function.identity()));
        Set<Long> retainedRoutineExerciseIds = new HashSet<>();

        for (RoutineExerciseCreateRequest exerciseRequest : exerciseRequests) {
            if (exerciseRequest.routineExerciseId() == null) {
                routine.addRoutineExercise(createRoutineExercise(exerciseRequest));
                continue;
            }

            RoutineExercise routineExercise = currentRoutineExercises.get(exerciseRequest.routineExerciseId());
            if (routineExercise == null) {
                throw new CustomException(ErrorCode.ROUTINE_EXERCISE_NOT_FOUND);
            }

            updateRoutineExercise(routineExercise, exerciseRequest);
            retainedRoutineExerciseIds.add(routineExercise.getId());
        }

        List<RoutineExercise> routineExercisesToRemove = routine.getRoutineExercises().stream()
                .filter(routineExercise -> routineExercise.getId() != null)
                .filter(routineExercise -> !routineExercise.isDeleted())
                .filter(routineExercise -> !retainedRoutineExerciseIds.contains(routineExercise.getId()))
                .toList();

        routineExercisesToRemove.forEach(RoutineExercise::delete);
    }

    /**
     * 기존 루틴 운동 row의 운동 종류와 목표치를 수정한다.
     */
    private void updateRoutineExercise(
            RoutineExercise routineExercise,
            RoutineExerciseCreateRequest exerciseRequest
    ) {
        Exercise exercise = findExercise(exerciseRequest.exerciseId());

        routineExercise.update(
                exercise,
                exerciseRequest.exerciseOrder(),
                exerciseRequest.targetReps(),
                exerciseRequest.targetSets(),
                exerciseRequest.restSeconds()
        );
    }

    /**
     * 수정 요청에 같은 루틴 운동 ID가 중복 포함되지 않았는지 확인한다.
     */
    private void validateRoutineExerciseIds(List<RoutineExerciseCreateRequest> exercises) {
        Set<Long> routineExerciseIds = new HashSet<>();
        boolean hasDuplicateId = exercises.stream()
                .map(RoutineExerciseCreateRequest::routineExerciseId)
                .filter(routineExerciseId -> routineExerciseId != null)
                .anyMatch(routineExerciseId -> !routineExerciseIds.add(routineExerciseId));

        if (hasDuplicateId) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }

    /**
     * 루틴 내 운동 순서가 중복되지 않았는지 확인한다.
     */
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
