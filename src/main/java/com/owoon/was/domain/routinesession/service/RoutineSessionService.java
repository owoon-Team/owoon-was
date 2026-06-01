package com.owoon.was.domain.routinesession.service;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import com.owoon.was.domain.feedbacklog.entity.FeedbackLog;
import com.owoon.was.domain.postureerrorlog.entity.PostureErrorLog;
import com.owoon.was.domain.routine.entity.Routine;
import com.owoon.was.domain.routine.entity.RoutineExercise;
import com.owoon.was.domain.routine.repository.RoutineRepository;
import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;
import com.owoon.was.domain.routinesession.dto.request.RoutineSessionCreateRequest;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionResponse;
import com.owoon.was.domain.routinesession.dto.response.RoutineSessionSummaryResponse;
import com.owoon.was.domain.routinesession.entity.RoutineSession;
import com.owoon.was.domain.routinesession.repository.RoutineSessionRepository;
import com.owoon.was.domain.user.entity.User;
import com.owoon.was.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineSessionService {

    private final RoutineSessionRepository routineSessionRepository;
    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;

    /**
     * 운동 종료 후 앱에서 생성한 루틴 실행 요약 결과를 저장한다.
     */
    @Transactional
    public RoutineSessionResponse createRoutineSession(Long userId, RoutineSessionCreateRequest request) {
        User user = findUser(userId);
        Routine routine = findRoutine(userId, request.routineId());

        RoutineSession routineSession = RoutineSession.builder()
                .user(user)
                .routine(routine)
                .totalTargetReps(request.totalTargetReps())
                .totalCompletedReps(request.totalCompletedReps())
                .totalNormalReps(request.totalNormalReps())
                .totalErrorReps(request.totalErrorReps())
                .averageAccuracyScore(request.averageAccuracyScore())
                .totalDurationSeconds(request.totalDurationSeconds())
                .startedAt(request.startedAt())
                .endedAt(request.endedAt())
                .build();

        Map<Long, RoutineExercise> routineExercises = routine.getRoutineExercises().stream()
                .collect(Collectors.toMap(RoutineExercise::getId, Function.identity()));

        request.exerciseResults().forEach(resultRequest -> {
            RoutineExerciseResult routineExerciseResult = RoutineExerciseResult.builder()
                        .routineExercise(findRoutineExercise(routineExercises, resultRequest.routineExerciseId()))
                        .completedReps(resultRequest.completedReps())
                        .completedSets(resultRequest.completedSets())
                        .normalReps(resultRequest.normalReps())
                        .errorReps(resultRequest.errorReps())
                        .accuracyScore(resultRequest.accuracyScore())
                        .durationSeconds(resultRequest.durationSeconds())
                        .startedAt(resultRequest.startedAt())
                        .endedAt(resultRequest.endedAt())
                        .build();

            if (resultRequest.postureErrorLogs() != null) {
                resultRequest.postureErrorLogs().forEach(errorLogRequest ->
                        routineExerciseResult.addPostureErrorLog(PostureErrorLog.builder()
                                .setNumber(errorLogRequest.setNumber())
                                .repNumber(errorLogRequest.repNumber())
                                .errorType(errorLogRequest.errorType())
                                .errorMessage(errorLogRequest.errorMessage())
                                .build())
                );
            }

            if (resultRequest.feedbackLogs() != null) {
                resultRequest.feedbackLogs().forEach(feedbackLogRequest ->
                        routineExerciseResult.addFeedbackLog(FeedbackLog.builder()
                                .setNumber(feedbackLogRequest.setNumber())
                                .repNumber(feedbackLogRequest.repNumber())
                                .feedbackType(feedbackLogRequest.feedbackType())
                                .feedbackMessage(feedbackLogRequest.feedbackMessage())
                                .severity(feedbackLogRequest.severity())
                                .feedbackSource(feedbackLogRequest.feedbackSource())
                                .build())
                );
            }

            routineSession.addRoutineExerciseResult(routineExerciseResult);
        });

        return RoutineSessionResponse.from(routineSessionRepository.save(routineSession));
    }

    /**
     * 회원 ID로 루틴 실행 기록 목록을 조회한다.
     */
    public List<RoutineSessionSummaryResponse> getRoutineSessions(Long userId) {
        validateUserExists(userId);

        return routineSessionRepository.findAllByUserIdOrderByStartedAtDesc(userId).stream()
                .map(RoutineSessionSummaryResponse::from)
                .toList();
    }

    /**
     * 회원 ID와 루틴 실행 기록 ID로 루틴 실행 상세 결과를 조회한다.
     */
    public RoutineSessionResponse getRoutineSession(Long userId, Long sessionId) {
        validateUserExists(userId);

        return RoutineSessionResponse.from(findRoutineSession(userId, sessionId));
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

    private Routine findRoutine(Long userId, Long routineId) {
        return routineRepository.findByIdAndUserId(routineId, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
    }

    private RoutineSession findRoutineSession(Long userId, Long sessionId) {
        return routineSessionRepository.findByIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_SESSION_NOT_FOUND));
    }

    private RoutineExercise findRoutineExercise(Map<Long, RoutineExercise> routineExercises, Long routineExerciseId) {
        RoutineExercise routineExercise = routineExercises.get(routineExerciseId);
        if (routineExercise == null) {
            throw new CustomException(ErrorCode.ROUTINE_EXERCISE_NOT_FOUND);
        }
        return routineExercise;
    }
}
