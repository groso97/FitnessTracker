package com.example.FitnessTracker.UserExercise;


public record UserExerciseDetailsResponseDTO(
        String exerciseName,
        String exerciseCategory,
        String description,
        String exerciseEquipment,
        Integer sets,
        Integer reps,
        Double weight
) {
}
