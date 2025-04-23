package com.example.FitnessTracker.TrainingPlan;

import com.example.FitnessTracker.TrainingPlanExercise.TrainingPlanExercise;
import com.example.FitnessTracker.User.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingPlanMapper {

    public TrainingPlan toEntity(TrainingPlanDTO trainingPlanDTO, User user) {
        return new TrainingPlan(
                trainingPlanDTO.name(),
                trainingPlanDTO.description(),
                trainingPlanDTO.durationWeeks(),
                LocalDate.now(),
                user,
                List.of() // prazna lista
        );
    }

    public TrainingPlan toEntity(TrainingPlanDTO trainingPlanDTO, User user, List<TrainingPlanExercise> trainingPlanExercises){
        return new TrainingPlan(
                trainingPlanDTO.name(),
                trainingPlanDTO.description(),
                trainingPlanDTO.durationWeeks(),
                LocalDate.now(),
                user,
                trainingPlanExercises != null ? trainingPlanExercises : List.of()
        );
    }

    public TrainingPlanResponseDTO toTrainingPlanResponseDTO(TrainingPlan trainingPlan){
        return new TrainingPlanResponseDTO(
                trainingPlan.getName(),
                trainingPlan.getDescription(),
                trainingPlan.getDurationWeeks(),
                trainingPlan.getUser().getFirstName() + " " + trainingPlan.getUser().getLastName()
        );
    }


}
