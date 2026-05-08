package com.owoon.was.domain.routine.entity;

import com.owoon.was.domain.exercise.entity.Exercise;
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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine_exercise")
public class RoutineExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise_order", nullable = false)
    private Integer exerciseOrder;

    @Column(name = "target_reps", nullable = false)
    private Integer targetReps;

    @Column(name = "target_sets", nullable = false)
    private Integer targetSets;

    @Column(name = "rest_seconds")
    private Integer restSeconds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Builder
    private RoutineExercise(
            Exercise exercise,
            Integer exerciseOrder,
            Integer targetReps,
            Integer targetSets,
            Integer restSeconds
    ) {
        this.exercise = exercise;
        this.exerciseOrder = exerciseOrder;
        this.targetReps = targetReps;
        this.targetSets = targetSets;
        this.restSeconds = restSeconds;
    }

    void assignRoutine(Routine routine) {
        this.routine = routine;
    }
}
