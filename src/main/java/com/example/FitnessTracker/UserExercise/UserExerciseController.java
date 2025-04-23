package com.example.FitnessTracker.UserExercise;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-exercises")
public class UserExerciseController {

    private final UserExerciseService userExerciseService;


    public UserExerciseController(UserExerciseService userExerciseService) {
        this.userExerciseService = userExerciseService;
    }

    @GetMapping
    public List<UserExerciseResponseDTO> findAllUserExercises(){
        return userExerciseService.findAllUserExercises();
    }

    @GetMapping("{userExerciseId}")
    public UserExerciseResponseDTO findUserExerciseById(@PathVariable Long userExerciseId){
        return userExerciseService.findUserExerciseById(userExerciseId);

    }

    @DeleteMapping("{userExerciseId}")
    public ResponseEntity<String> deleteUserExerciseById(@PathVariable Long userExerciseId){
        String message = userExerciseService.deleteUserExerciseById(userExerciseId);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<UserExerciseResponseDTO> createUserExercise(@Valid @RequestBody UserExerciseDTO userExerciseDTO){
        UserExerciseResponseDTO userExerciseResponseDTO = userExerciseService.createUserExercise(userExerciseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userExerciseResponseDTO);
    }

    @PutMapping("{userExerciseId}")
    public ResponseEntity<UserExerciseResponseDTO> updateUserExerciseById(@Valid @PathVariable Long userExerciseId, @RequestBody UserExerciseDTO userExerciseDTO){
        UserExerciseResponseDTO updated = userExerciseService.updateUserExercise(userExerciseId,userExerciseDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
