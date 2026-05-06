package com.owoon.was.domain.exercise.controller;

import com.owoon.was.domain.exercise.controller.api.ExerciseApi;
import com.owoon.was.domain.exercise.dto.response.ExerciseResponse;
import com.owoon.was.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercises")
public class ExerciseController implements ExerciseApi {

    private final ExerciseService exerciseService;

    @Override
    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }
}
