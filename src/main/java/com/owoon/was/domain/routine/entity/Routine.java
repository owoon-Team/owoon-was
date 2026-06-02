package com.owoon.was.domain.routine.entity;

import com.owoon.was.domain.user.entity.User;
import jakarta.persistence.CascadeType;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routines")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineExercise> routineExercises = new ArrayList<>();

    @Builder
    private Routine(User user, String name, String description) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public void addRoutineExercise(RoutineExercise routineExercise) {
        routineExercises.add(routineExercise);
        routineExercise.assignRoutine(this);
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void clearRoutineExercises() {
        routineExercises.clear();
    }
}
