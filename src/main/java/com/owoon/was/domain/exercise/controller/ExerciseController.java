package com.owoon.was.domain.exercise.controller;

import com.owoon.was.domain.exercise.controller.api.ExerciseApi;
import com.owoon.was.domain.exercise.dto.request.ExerciseCreateRequest;
import com.owoon.was.domain.exercise.dto.response.ExerciseResponse;
import com.owoon.was.domain.exercise.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercises")
public class ExerciseController implements ExerciseApi {

    private final ExerciseService exerciseService;

    @Override
    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(@RequestBody @Valid ExerciseCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.createExercise(request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }
}
