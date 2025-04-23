package com.example.FitnessTracker.UserExercise;

public record UserExerciseResponseDTO(
        Long userExerciseId,
        Long userId,
        Long exerciseId,
        Integer sets,
        Integer reps,
        Double weight
) {
}
