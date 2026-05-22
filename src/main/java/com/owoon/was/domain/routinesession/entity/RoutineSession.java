package com.owoon.was.domain.routinesession.entity;

import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;
import com.owoon.was.domain.routine.entity.Routine;
import com.owoon.was.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine_sessions")
public class RoutineSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_target_reps", nullable = false)
    private Integer totalTargetReps;

    @Column(name = "total_completed_reps", nullable = false)
    private Integer totalCompletedReps;

    @Column(name = "total_normal_reps", nullable = false)
    private Integer totalNormalReps;

    @Column(name = "total_error_reps", nullable = false)
    private Integer totalErrorReps;

    @Column(name = "average_accuracy_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal averageAccuracyScore;

    @Column(name = "total_duration_seconds", nullable = false)
    private Integer totalDurationSeconds;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at", nullable = false)
    private LocalDateTime endedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @OneToMany(mappedBy = "routineSession", cascade = ALL, orphanRemoval = true)
    private List<RoutineExerciseResult> routineExerciseResults = new ArrayList<>();

    @Builder
    private RoutineSession(
            User user,
            Routine routine,
            Integer totalTargetReps,
            Integer totalCompletedReps,
            Integer totalNormalReps,
            Integer totalErrorReps,
            BigDecimal averageAccuracyScore,
            Integer totalDurationSeconds,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        this.user = user;
        this.routine = routine;
        this.totalTargetReps = totalTargetReps;
        this.totalCompletedReps = totalCompletedReps;
        this.totalNormalReps = totalNormalReps;
        this.totalErrorReps = totalErrorReps;
        this.averageAccuracyScore = averageAccuracyScore;
        this.totalDurationSeconds = totalDurationSeconds;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.createdAt = LocalDateTime.now();
    }

    public void addRoutineExerciseResult(RoutineExerciseResult routineExerciseResult) {
        routineExerciseResults.add(routineExerciseResult);
        routineExerciseResult.assignRoutineSession(this);
    }
}
