package com.example.FitnessTracker.TrainingPlanExercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingPlanExerciseRepository extends JpaRepository<TrainingPlanExercise, Long> {
    Optional<TrainingPlanExercise> findByTrainingPlan_TrainingPlanIdAndExercise_ExerciseId(Long trainingPlanId, Long exerciseId);
    List<TrainingPlanExercise> findAllByTrainingPlan_TrainingPlanId(Long trainingPlanId);
}
