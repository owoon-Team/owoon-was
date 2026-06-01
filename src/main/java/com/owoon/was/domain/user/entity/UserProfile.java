package com.owoon.was.domain.user.entity;

import com.owoon.was.domain.user.entity.enums.ExerciseLevel;
import com.owoon.was.domain.user.entity.enums.Gender;
import com.owoon.was.domain.user.entity.enums.MainGoal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "exercise_level", nullable = false)
    private ExerciseLevel exerciseLevel;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "height_cm", nullable = false, precision = 5, scale = 2)
    private BigDecimal heightCm;

    @Column(name = "weight_kg", nullable = false, precision = 5, scale = 2)
    private BigDecimal weightKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_goal", nullable = false)
    private MainGoal mainGoal;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private UserProfile(
            User user,
            ExerciseLevel exerciseLevel,
            LocalDate birthDate,
            BigDecimal heightCm,
            BigDecimal weightKg,
            Gender gender,
            MainGoal mainGoal
    ) {
        this.user = user;
        this.exerciseLevel = exerciseLevel;
        this.birthDate = birthDate;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.gender = gender;
        this.mainGoal = mainGoal;
        this.createdAt = LocalDateTime.now();
    }

    public void update(
            ExerciseLevel exerciseLevel,
            LocalDate birthDate,
            BigDecimal heightCm,
            BigDecimal weightKg,
            Gender gender,
            MainGoal mainGoal
    ) {
        this.exerciseLevel = exerciseLevel;
        this.birthDate = birthDate;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.gender = gender;
        this.mainGoal = mainGoal;
    }
}
