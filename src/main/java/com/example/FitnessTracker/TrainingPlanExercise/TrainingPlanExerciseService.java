package com.example.FitnessTracker.TrainingPlanExercise;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.Exercise.ExerciseRepository;
import com.example.FitnessTracker.TrainingPlan.TrainingPlan;
import com.example.FitnessTracker.TrainingPlan.TrainingPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TrainingPlanExerciseService {
    private final TrainingPlanExerciseRepository trainingPlanExerciseRepository;
    private final TrainingPlanExerciseMapper trainingPlanExerciseMapper;
    private final TrainingPlanRepository trainingPlanRepository;
    private final ExerciseRepository exerciseRepository;

    public TrainingPlanExerciseService(TrainingPlanExerciseRepository trainingPlanExerciseRepository, TrainingPlanExerciseMapper trainingPlanExerciseMapper, TrainingPlanRepository trainingPlanRepository, ExerciseRepository exerciseRepository) {
        this.trainingPlanExerciseRepository = trainingPlanExerciseRepository;
        this.trainingPlanExerciseMapper = trainingPlanExerciseMapper;
        this.trainingPlanRepository = trainingPlanRepository;
        this.exerciseRepository = exerciseRepository;
    }


    //	Da vratiš sve vježbe s sets/reps za taj plan.
    public List<TrainingPlanExerciseResponseDTO> getExercisesByTrainingPlanId(Long trainingPlanId){
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan with ID: " + trainingPlanId + " does not exist."));

        List<TrainingPlanExercise> exercises = trainingPlanExerciseRepository.findAllByTrainingPlan_TrainingPlanId(trainingPlanId);

        if(exercises.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no exercises in a training plan with ID: " + trainingPlanId);
        }

        return exercises.stream()
                .map(trainingPlanExerciseMapper::toTrainingPlanExerciseResponseDTO)
                .toList();
    }

    //Da korisnik može izmijeniti broj reps/sets bez brisanja.
    public TrainingPlanExerciseResponseDTO updateTrainingPlanExercise(Long trainingPlanId, Long exerciseId, Integer sets, Integer reps){
        TrainingPlanExercise trainingPlanExercise = trainingPlanExerciseRepository
                .findByTrainingPlan_TrainingPlanIdAndExercise_ExerciseId(trainingPlanId, exerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan exercise not found"));

        trainingPlanExercise.setSets(sets);
        trainingPlanExercise.setReps(reps);

        trainingPlanExerciseRepository.save(trainingPlanExercise);

        return trainingPlanExerciseMapper.toTrainingPlanExerciseResponseDTO(trainingPlanExercise);
    }




    public TrainingPlanExerciseResponseDTO createTrainingPlanExercise(TrainingPlanExerciseDTO trainingPlanExerciseDTO){
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanExerciseDTO.trainingPlanId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan not found."));

        Exercise exercise = exerciseRepository.findById(trainingPlanExerciseDTO.exerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found."));

        TrainingPlanExercise trainingPlanExercise = trainingPlanExerciseMapper.toEntity(trainingPlanExerciseDTO,trainingPlan,exercise);

        trainingPlanExerciseRepository.save(trainingPlanExercise);

        return trainingPlanExerciseMapper.toTrainingPlanExerciseResponseDTO(trainingPlanExercise);
    }

    public String deleteExerciseFromTrainingPlanExerciseById(Long trainingPlanId, Long exerciseId){
        TrainingPlanExercise trainingPlanExercise = trainingPlanExerciseRepository.findByTrainingPlan_TrainingPlanIdAndExercise_ExerciseId(trainingPlanId, exerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training plan exercise not found"));

        String trainingPlanName = trainingPlanExercise.getTrainingPlan().getName();
        String exerciseName = trainingPlanExercise.getExercise().getName();

        trainingPlanExerciseRepository.delete(trainingPlanExercise);

        return "Exercise: " + exerciseName + " was successfully deleted from the training plan: " + trainingPlanName;
    }
}
