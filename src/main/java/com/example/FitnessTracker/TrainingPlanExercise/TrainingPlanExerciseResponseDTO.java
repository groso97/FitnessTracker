package com.example.FitnessTracker.TrainingPlanExercise;



public record TrainingPlanExerciseResponseDTO(
        Long trainingPlanId,
        Long exerciseId,
        String exerciseName,
        Integer reps,
        Integer sets
) {
}
