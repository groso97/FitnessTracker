package com.example.FitnessTracker.TrainingPlanExercise;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.TrainingPlan.TrainingPlan;
import org.springframework.stereotype.Service;

@Service
public class TrainingPlanExerciseMapper {

    public TrainingPlanExercise toEntity(TrainingPlanExerciseDTO trainingPlanExerciseDTO, TrainingPlan trainingPlan, Exercise exercise){
        return new TrainingPlanExercise(
                trainingPlan,
                exercise,
                trainingPlanExerciseDTO.sets(),
                trainingPlanExerciseDTO.reps()
        );
    }

    public TrainingPlanExerciseResponseDTO toTrainingPlanExerciseResponseDTO(TrainingPlanExercise trainingPlanExercise){
        return new TrainingPlanExerciseResponseDTO(
                trainingPlanExercise.getId(),
                trainingPlanExercise.getExercise().getExerciseId(),
                trainingPlanExercise.getExercise().getName(),
                trainingPlanExercise.getReps(),
                trainingPlanExercise.getSets()
        );
    }
}
