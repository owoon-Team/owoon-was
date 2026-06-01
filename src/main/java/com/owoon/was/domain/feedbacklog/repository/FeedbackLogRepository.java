package com.owoon.was.domain.feedbacklog.repository;

import com.owoon.was.domain.feedbacklog.entity.FeedbackLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 회원별 루틴 운동 실행 결과의 피드백 로그 조회를 담당하는 repository.
 */
public interface FeedbackLogRepository extends JpaRepository<FeedbackLog, Long> {

    /**
     * 회원 ID와 루틴 실행 기록 ID, 운동별 실행 결과 ID로 피드백 로그 목록을 조회한다.
     */
    List<FeedbackLog> findAllByRoutineExerciseResultIdAndRoutineExerciseResultRoutineSessionIdAndRoutineExerciseResultRoutineSessionUserIdOrderByCreatedAtAscIdAsc(
            Long resultId,
            Long sessionId,
            Long userId
    );
}
