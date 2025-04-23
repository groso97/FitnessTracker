package com.example.FitnessTracker.Exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseDTO(
        @NotBlank(message = "Name of the exercise cannot be blank.")
        String name,
        @NotNull(message = "Exercise category cannot be null.")
        ExerciseCategory category,
        @NotBlank(message = "Exercise must have description.")
        String description,
        @NotNull(message = "Exercise equipment cannot be null.")
        ExerciseEquipment equipment
) {
}
