package com.example.FitnessTracker.TrainingPlan;

public record TrainingPlanResponseDTO(
        String name,
        String description,
        Integer durationWeeks,
        String userFirstAndLastName
) {
}
