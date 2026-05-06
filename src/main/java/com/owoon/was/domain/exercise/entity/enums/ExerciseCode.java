package com.owoon.was.domain.exercise.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseCode {
    PUSH_UP("팔굽혀펴기"),
    SIT_UP("윗몸일으키기"),
    SQUAT("스쿼트"),
    LUNGE("런지"),
    HIP_THRUST("힙쓰러스트");

    private final String description;
}
