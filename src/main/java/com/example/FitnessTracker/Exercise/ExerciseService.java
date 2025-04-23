package com.example.FitnessTracker.Exercise;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public List<ExerciseResponseDTO> findAllExercises(){
        List<Exercise> exercises = exerciseRepository.findAll();

        if(exercises.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no exercises in the database.");

        }
        return exercises.stream().map(exerciseMapper::toExerciseResponseDTO).collect(Collectors.toList());
    }

    public ExerciseResponseDTO findExerciseById(Long exerciseId){
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There is no existing exercise with ID: " + exerciseId));

        return exerciseMapper.toExerciseResponseDTO(exercise);
    }

    public ExerciseResponseDTO createExercise(ExerciseDTO exerciseDTO){
        Exercise exercise = exerciseMapper.toEntity(exerciseDTO);
        Exercise savedExercise = exerciseRepository.save(exercise);

        return exerciseMapper.toExerciseResponseDTO(savedExercise);
    }

    public String deleteExerciseById(Long exerciseId){
        if(!exerciseRepository.existsById(exerciseId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise with ID: " + exerciseId + " does not exist.");
        }
        exerciseRepository.deleteById(exerciseId);

        return "Exercise with ID " + exerciseId + " was successfully deleted.";
    }

    public ExerciseResponseDTO updateExerciseById(Long exerciseId, ExerciseUpdateDTO exerciseUpdateDTO){
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));

        if(exerciseUpdateDTO.name() != null) exercise.setName(exerciseUpdateDTO.name());
        if(exerciseUpdateDTO.category() != null) exercise.setCategory(exerciseUpdateDTO.category());
        if(exerciseUpdateDTO.description() != null) exercise.setDescription(exerciseUpdateDTO.description());
        if(exerciseUpdateDTO.equipment() != null) exercise.setEquipment(exerciseUpdateDTO.equipment());

        exerciseRepository.save(exercise);

        return exerciseMapper.toExerciseResponseDTO(exercise);
    }

}
