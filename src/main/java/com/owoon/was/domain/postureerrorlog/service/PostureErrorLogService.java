package com.owoon.was.domain.postureerrorlog.service;

import com.owoon.was.common.exception.CustomException;
import com.owoon.was.common.exception.error.ErrorCode;
import com.owoon.was.domain.postureerrorlog.dto.response.PostureErrorLogResponse;
import com.owoon.was.domain.postureerrorlog.repository.PostureErrorLogRepository;
import com.owoon.was.domain.routineexerciseresult.repository.RoutineExerciseResultRepository;
import com.owoon.was.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostureErrorLogService {

    private final PostureErrorLogRepository postureErrorLogRepository;
    private final RoutineExerciseResultRepository routineExerciseResultRepository;
    private final UserRepository userRepository;

    /**
     * 회원 ID와 루틴 실행 기록 ID, 운동별 실행 결과 ID로 자세 오류 로그 목록을 조회한다.
     */
    public List<PostureErrorLogResponse> getPostureErrorLogs(Long userId, Long sessionId, Long resultId) {
        validateUserExists(userId);
        validateRoutineExerciseResultExists(userId, sessionId, resultId);

        return postureErrorLogRepository
                .findAllByRoutineExerciseResultIdAndRoutineExerciseResultRoutineSessionIdAndRoutineExerciseResultRoutineSessionUserIdOrderBySetNumberAscRepNumberAsc(
                        resultId,
                        sessionId,
                        userId
                )
                .stream()
                .map(PostureErrorLogResponse::from)
                .toList();
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    private void validateRoutineExerciseResultExists(Long userId, Long sessionId, Long resultId) {
        if (!routineExerciseResultRepository.existsByIdAndRoutineSessionIdAndRoutineSessionUserId(
                resultId,
                sessionId,
                userId
        )) {
            throw new CustomException(ErrorCode.ROUTINE_EXERCISE_RESULT_NOT_FOUND);
        }
    }
}
