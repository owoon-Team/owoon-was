package com.owoon.was.domain.postureerrorlog.entity;

import com.owoon.was.domain.routineexerciseresult.entity.RoutineExerciseResult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "posture_error_logs")
public class PostureErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(name = "rep_number", nullable = false)
    private Integer repNumber;

    @Column(name = "error_type", nullable = false, length = 100)
    private String errorType;

    @Column(name = "error_message", nullable = false, length = 500)
    private String errorMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_exercise_result_id", nullable = false)
    private RoutineExerciseResult routineExerciseResult;

    @Builder
    private PostureErrorLog(
            Integer setNumber,
            Integer repNumber,
            String errorType,
            String errorMessage
    ) {
        this.setNumber = setNumber;
        this.repNumber = repNumber;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.createdAt = LocalDateTime.now();
    }

    public void assignRoutineExerciseResult(RoutineExerciseResult routineExerciseResult) {
        this.routineExerciseResult = routineExerciseResult;
    }
}
