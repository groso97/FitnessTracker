package com.example.FitnessTracker.Exercise;

public record ExerciseResponseDTO(
        String name,
        ExerciseCategory category,
        String description,
        ExerciseEquipment equipment
) {
}
