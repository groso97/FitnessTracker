package com.example.FitnessTracker.Exercise;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;


    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<ExerciseResponseDTO> findAllExercises(){
        return exerciseService.findAllExercises();
    }

    @GetMapping("{exerciseId}")
    public ExerciseResponseDTO findExerciseById(@PathVariable Long exerciseId){
        return exerciseService.findExerciseById(exerciseId);
    }

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> createExercise(@Valid @RequestBody ExerciseDTO exerciseDTO){
        ExerciseResponseDTO exerciseResponseDTO = exerciseService.createExercise(exerciseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseResponseDTO);
    }

    @DeleteMapping("{exerciseId}")
    public ResponseEntity<String> deleteExerciseById(@PathVariable Long exerciseId){
        String message = exerciseService.deleteExerciseById(exerciseId);
        return ResponseEntity.ok(message);
    }

    @PutMapping("{exerciseId}")
    public ResponseEntity<ExerciseResponseDTO> updateExerciseById(@PathVariable Long exerciseId, @RequestBody ExerciseUpdateDTO exerciseUpdateDTO){
        ExerciseResponseDTO exerciseResponseDTO = exerciseService.updateExerciseById(exerciseId,exerciseUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK).body(exerciseResponseDTO);
    }
}
