package com.example.FitnessTracker.UserExercise;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserExerciseDTO(
        @NotNull(message = "User ID cannot be null")
        Long userId,

        @NotNull(message = "Exercise ID cannot be null")
        Long exerciseId,

        @NotNull(message = "Sets cannot be null")
        @Min(value = 1, message = "There must be at least 1 set")
        Integer sets,

        @NotNull(message = "Reps cannot be null")
        @Min(value = 1, message = "There must be at least 1 rep")
        Integer reps,

        @NotNull(message = "Weight cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
        Double weight
) {
}
