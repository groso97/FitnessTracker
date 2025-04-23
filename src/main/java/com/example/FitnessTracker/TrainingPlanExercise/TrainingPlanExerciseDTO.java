package com.example.FitnessTracker.TrainingPlanExercise;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TrainingPlanExerciseDTO(
        @NotNull(message = "Training plan ID cannot be null")
        Long trainingPlanId,
        @NotNull(message = "Exercise ID cannot be null")
        Long exerciseId,
        @NotNull(message = "Sets cannot be null")
        @Min(value = 1, message = "There must be at least 1 set")
        Integer sets,
        @NotNull(message = "Reps cannot be null")
        @Min(value = 1, message = "There must be at least 1 rep")
        Integer reps
) {
}
