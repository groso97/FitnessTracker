package com.example.FitnessTracker.Exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ExerciseUpdateDTO(
        @NotBlank(message = "Name of the exercise cannot be blank.")
        String name,
        @NotBlank(message = "Exercise category cannot be blank.")
        @Pattern(regexp = "^(LEGS|CORE|CHEST|ARMS|SHOULDERS|BACK)$", message = "Exercise category can be just one of these: LEGS, CORE, CHEST, ARMS, SHOULDERS, BACK")
        ExerciseCategory category,
        @NotBlank(message = "Exercise must have description.")
        String description,
        @NotBlank(message = "Exercise equipment cannot be blank.")
        @Pattern(regexp = "^(NO_EQUIPMENT|DUMBBELLS|BARBELL|KETTLEBELL|RESISTANCE_BANDS|MACHINES)$", message = "Exercise equipment can be just one of these: NO_EQUIPMENT, DUMBBELLS, BARBELL, KETTLEBELL, RESISTANCE_BANDS, MACHINES")
        ExerciseEquipment equipment
) {
}
