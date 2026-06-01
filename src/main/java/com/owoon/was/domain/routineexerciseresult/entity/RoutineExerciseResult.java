package com.owoon.was.domain.routineexerciseresult.entity;

import com.owoon.was.domain.feedbacklog.entity.FeedbackLog;
import com.owoon.was.domain.postureerrorlog.entity.PostureErrorLog;
import com.owoon.was.domain.routine.entity.RoutineExercise;
import com.owoon.was.domain.routinesession.entity.RoutineSession;
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
@Table(name = "routine_exercise_results")
public class RoutineExerciseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise_order_snapshot", nullable = false)
    private Integer exerciseOrderSnapshot;

    @Column(name = "target_reps_snapshot", nullable = false)
    private Integer targetRepsSnapshot;

    @Column(name = "target_sets_snapshot", nullable = false)
    private Integer targetSetsSnapshot;

    @Column(name = "rest_seconds_snapshot")
    private Integer restSecondsSnapshot;

    @Column(name = "completed_reps", nullable = false)
    private Integer completedReps;

    @Column(name = "completed_sets", nullable = false)
    private Integer completedSets;

    @Column(name = "normal_reps", nullable = false)
    private Integer normalReps;

    @Column(name = "error_reps", nullable = false)
    private Integer errorReps;

    @Column(name = "accuracy_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal accuracyScore;

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at", nullable = false)
    private LocalDateTime endedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_session_id", nullable = false)
    private RoutineSession routineSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_exercise_id", nullable = false)
    private RoutineExercise routineExercise;

    @OneToMany(mappedBy = "routineExerciseResult", cascade = ALL, orphanRemoval = true)
    private List<PostureErrorLog> postureErrorLogs = new ArrayList<>();

    @OneToMany(mappedBy = "routineExerciseResult", cascade = ALL, orphanRemoval = true)
    private List<FeedbackLog> feedbackLogs = new ArrayList<>();

    @Builder
    private RoutineExerciseResult(
            RoutineExercise routineExercise,
            Integer completedReps,
            Integer completedSets,
            Integer normalReps,
            Integer errorReps,
            BigDecimal accuracyScore,
            Integer durationSeconds,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        this.routineExercise = routineExercise;
        this.exerciseOrderSnapshot = routineExercise.getExerciseOrder();
        this.targetRepsSnapshot = routineExercise.getTargetReps();
        this.targetSetsSnapshot = routineExercise.getTargetSets();
        this.restSecondsSnapshot = routineExercise.getRestSeconds();
        this.completedReps = completedReps;
        this.completedSets = completedSets;
        this.normalReps = normalReps;
        this.errorReps = errorReps;
        this.accuracyScore = accuracyScore;
        this.durationSeconds = durationSeconds;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.createdAt = LocalDateTime.now();
    }

    public void assignRoutineSession(RoutineSession routineSession) {
        this.routineSession = routineSession;
    }

    public void addPostureErrorLog(PostureErrorLog postureErrorLog) {
        postureErrorLogs.add(postureErrorLog);
        postureErrorLog.assignRoutineExerciseResult(this);
    }

    public void addFeedbackLog(FeedbackLog feedbackLog) {
        feedbackLogs.add(feedbackLog);
        feedbackLog.assignRoutineExerciseResult(this);
    }
}
