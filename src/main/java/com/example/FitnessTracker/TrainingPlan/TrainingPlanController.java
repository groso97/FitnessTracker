package com.example.FitnessTracker.TrainingPlan;

import com.example.FitnessTracker.Exercise.ExerciseResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training-plans")
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;


    public TrainingPlanController(TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @GetMapping
    public List<TrainingPlanResponseDTO> findAllTrainingPlans(){
        return trainingPlanService.findAllTrainingPlans();
    }

    @GetMapping("{trainingPlanId}")
    public TrainingPlanResponseDTO findTrainingPlanById(@PathVariable Long trainingPlanId){
        return trainingPlanService.findTrainingPlanById(trainingPlanId);
    }

//    fetch all exercises for the given training plans ID
    @GetMapping("{trainingPlanId}/exercises")
    public List<ExerciseResponseDTO> findExercisesByTrainingPlanId(@PathVariable Long trainingPlanId){
        return trainingPlanService.findExercisesByTrainingPlanId(trainingPlanId);
    }

    @PostMapping
    public ResponseEntity<TrainingPlanResponseDTO> createTrainingPlan(@Valid @RequestBody TrainingPlanDTO trainingPlanDTO){
        TrainingPlanResponseDTO trainingPlanResponseDTO = trainingPlanService.createTrainingPlan(trainingPlanDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainingPlanResponseDTO);
    }

    @DeleteMapping("{trainingPlanId}")
    public ResponseEntity<String> deleteTrainingPlanById (@PathVariable Long trainingPlanId){
        String message = trainingPlanService.deleteTrainingPlanById(trainingPlanId);

        return ResponseEntity.ok(message);
    }

    @PutMapping("{trainingPlanId}")
    public ResponseEntity<TrainingPlanResponseDTO> updateTrainingPlanById(@PathVariable Long trainingPlanId, @RequestBody TrainingPlanDTO  trainingPlanDTO){
        TrainingPlanResponseDTO trainingPlanResponseDTO = trainingPlanService.updateTrainingPlanById(trainingPlanId,trainingPlanDTO);

        return ResponseEntity.status(HttpStatus.OK).body(trainingPlanResponseDTO);
    }
}

