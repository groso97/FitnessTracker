package com.example.FitnessTracker.TrainingPlan;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TrainingPlanDTO(
        @NotBlank(message = "Training plans name cannot be blank.")
        String name,
        @NotBlank(message = "Training plan must have description.")
        String description,
        @Min(value = 1, message = "Training plan duration has to be at least 1 week.")
        Integer durationWeeks,
        @NotNull
        Long userId
) {
}
