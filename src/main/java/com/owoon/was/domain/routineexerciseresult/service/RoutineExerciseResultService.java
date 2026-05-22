package com.owoon.was.domain.routineexerciseresult.service;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import com.owoon.was.domain.routineexerciseresult.dto.response.RoutineExerciseResultResponse;
import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;
import com.owoon.was.domain.routineexerciseresult.repository.RoutineExerciseResultRepository;
import com.owoon.was.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineExerciseResultService {

    private final RoutineExerciseResultRepository routineExerciseResultRepository;
    private final UserRepository userRepository;

    /**
     * 회원 ID와 루틴 실행 기록 ID로 운동별 실행 결과 목록을 조회한다.
     */
    public List<RoutineExerciseResultResponse> getRoutineExerciseResults(Long userId, Long sessionId) {
        validateUserExists(userId);

        return routineExerciseResultRepository
                .findAllByRoutineSessionIdAndRoutineSessionUserIdOrderByExerciseOrderSnapshotAsc(sessionId, userId)
                .stream()
                .map(RoutineExerciseResultResponse::from)
                .toList();
    }

    /**
     * 회원 ID, 루틴 실행 기록 ID, 운동별 실행 결과 ID로 운동별 실행 결과를 조회한다.
     */
    public RoutineExerciseResultResponse getRoutineExerciseResult(Long userId, Long sessionId, Long resultId) {
        validateUserExists(userId);

        return RoutineExerciseResultResponse.from(findRoutineExerciseResult(userId, sessionId, resultId));
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    private RoutineExerciseResult findRoutineExerciseResult(Long userId, Long sessionId, Long resultId) {
        return routineExerciseResultRepository.findByIdAndRoutineSessionIdAndRoutineSessionUserId(
                        resultId,
                        sessionId,
                        userId
                )
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_EXERCISE_RESULT_NOT_FOUND));
    }
}
