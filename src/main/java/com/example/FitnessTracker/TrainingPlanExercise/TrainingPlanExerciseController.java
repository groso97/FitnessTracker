package com.example.FitnessTracker.TrainingPlanExercise;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/training-plan-exercises")
public class TrainingPlanExerciseController {
    private final TrainingPlanExerciseService trainingPlanExerciseService;


    public TrainingPlanExerciseController(TrainingPlanExerciseService trainingPlanExerciseService) {
        this.trainingPlanExerciseService = trainingPlanExerciseService;
    }

    //	Da vratiš sve vježbe s sets/reps za taj plan.
    @GetMapping("/{trainingPlanId}")
    public ResponseEntity<List<TrainingPlanExerciseResponseDTO>> getExercisesByPlan(@PathVariable Long trainingPlanId) {
        List<TrainingPlanExerciseResponseDTO> response = trainingPlanExerciseService.getExercisesByTrainingPlanId(trainingPlanId);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<TrainingPlanExerciseResponseDTO> createTrainingPlanExercise(@Valid @RequestBody TrainingPlanExerciseDTO trainingPlanExerciseDTO){
        TrainingPlanExerciseResponseDTO trainingPlanExerciseResponseDTO = trainingPlanExerciseService.createTrainingPlanExercise(trainingPlanExerciseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainingPlanExerciseResponseDTO);
    }

    //Da korisnik može izmijeniti broj reps/sets bez brisanja.
    @PutMapping("/{trainingPlanId}/exercise/{exerciseId}")
    public ResponseEntity<TrainingPlanExerciseResponseDTO> updateRepsAndSets(
            @PathVariable Long trainingPlanId,
            @PathVariable Long exerciseId,
            @RequestBody Map<String, Integer> updates
    ) {
        Integer sets = updates.get("sets");
        Integer reps = updates.get("reps");

        if (sets == null || reps == null || sets < 1 || reps < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sets and reps must be greater than 1");
        }

        TrainingPlanExerciseResponseDTO updated = trainingPlanExerciseService.updateTrainingPlanExercise(trainingPlanId, exerciseId, sets, reps);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("{trainingPlanId}/exercise/{exerciseId}")
    public ResponseEntity<String> deleteExerciseFromTrainingPlanExerciseById(@PathVariable Long trainingPlanId, @PathVariable Long exerciseId){
        String message = trainingPlanExerciseService.deleteExerciseFromTrainingPlanExerciseById(trainingPlanId,exerciseId);

        return ResponseEntity.ok(message);
    }
}
