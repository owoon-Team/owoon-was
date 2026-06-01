package com.owoon.was.domain.feedbacklog.entity;

import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSeverity;
import com.owoon.was.domain.feedbacklog.entity.enums.FeedbackSource;
import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "feedback_logs")
public class FeedbackLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "rep_number")
    private Integer repNumber;

    @Column(name = "feedback_type", nullable = false, length = 100)
    private String feedbackType;

    @Column(name = "feedback_message", nullable = false, length = 500)
    private String feedbackMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false, length = 20)
    private FeedbackSeverity severity;

    @Enumerated(EnumType.STRING)
    @Column(name = "feedback_source", nullable = false, length = 30)
    private FeedbackSource feedbackSource;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_exercise_result_id", nullable = false)
    private RoutineExerciseResult routineExerciseResult;

    @Builder
    private FeedbackLog(
            Integer setNumber,
            Integer repNumber,
            String feedbackType,
            String feedbackMessage,
            FeedbackSeverity severity,
            FeedbackSource feedbackSource
    ) {
        this.setNumber = setNumber;
        this.repNumber = repNumber;
        this.feedbackType = feedbackType;
        this.feedbackMessage = feedbackMessage;
        this.severity = severity;
        this.feedbackSource = feedbackSource;
        this.createdAt = LocalDateTime.now();
    }

    public void assignRoutineExerciseResult(RoutineExerciseResult routineExerciseResult) {
        this.routineExerciseResult = routineExerciseResult;
    }
}
