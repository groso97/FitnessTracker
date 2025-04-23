package com.example.FitnessTracker.Exercise;
import org.springframework.stereotype.Service;

@Service
public class ExerciseMapper {

    public Exercise toEntity(ExerciseDTO exerciseDTO){
        return new Exercise(
                exerciseDTO.name(),
                exerciseDTO.category(),
                exerciseDTO.description(),
                exerciseDTO.equipment(),
                null,
                null
        );
    }

    public ExerciseResponseDTO toExerciseResponseDTO(Exercise exercise){
        return new ExerciseResponseDTO(
                exercise.getName(),
                exercise.getCategory(),
                exercise.getDescription(),
                exercise.getEquipment()
        );

    }
}
