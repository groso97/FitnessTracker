package com.example.FitnessTracker.User;

import com.example.FitnessTracker.UserExercise.UserExerciseDetailsResponseDTO;
import com.example.FitnessTracker.UserExercise.UserExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserExerciseService userExerciseService;

    public UserController(UserService userService, UserExerciseService userExerciseService) {
        this.userService = userService;
        this.userExerciseService = userExerciseService;
    }

    @GetMapping
    public List<UserResponseDTO> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("{userId}")
    public UserResponseDTO findUserById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    //dohvati sve vjezbe korisnika na odredenom id-u
    @GetMapping("{userId}/exercises")
    public List<UserExerciseDetailsResponseDTO> findUserExercises(@PathVariable Long userId){
        return userExerciseService.getExercisesByUser(userId);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserResponseDTO userResponseDTO = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        String message = userService.deleteUserById(userId);
        return ResponseEntity.ok(message);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,@Valid @RequestBody UserUpdateDTO userUpdateDTO){
        UserResponseDTO userResponseDTO = userService.updateUser(userId,userUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);

    }
}
