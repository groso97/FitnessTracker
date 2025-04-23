package com.example.FitnessTracker.UserExercise;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.Exercise.ExerciseRepository;
import com.example.FitnessTracker.User.User;
import com.example.FitnessTracker.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserExerciseService {
    private final UserExerciseRepository userExerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserExerciseMapper userExerciseMapper;

    public UserExerciseService(UserExerciseRepository userExerciseRepository, UserRepository userRepository, ExerciseRepository exerciseRepository, UserExerciseMapper userExerciseMapper) {
        this.userExerciseRepository = userExerciseRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.userExerciseMapper = userExerciseMapper;
    }

    public List<UserExerciseResponseDTO> findAllUserExercises(){
        List<UserExercise> userExercises = userExerciseRepository.findAll();

        if(userExercises.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no existing UserExercises in database");
        }
        return userExercises.stream().map(userExerciseMapper::toUserExerciseResponseDTO).toList();
    }

    public UserExerciseResponseDTO findUserExerciseById(Long userExerciseId){
        UserExercise userExercise = userExerciseRepository.findById(userExerciseId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no existing exercise with ID: " + userExerciseId));

        return userExerciseMapper.toUserExerciseResponseDTO(userExercise);
    }

    public String deleteUserExerciseById(Long userExerciseId){
        UserExercise userExercise = userExerciseRepository.findById(userExerciseId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserExercise with ID: " + userExerciseId + " does not exist."));

        userExerciseRepository.deleteById(userExerciseId);

        return "UserExercise with ID " + userExerciseId + " was successfully deleted.";
    }

    public UserExerciseResponseDTO createUserExercise(UserExerciseDTO userExerciseDTO){
        User user = userRepository.findById(userExerciseDTO.userId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Exercise exercise = exerciseRepository.findById(userExerciseDTO.exerciseId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));

        UserExercise userExercise = userExerciseMapper.toEntity(userExerciseDTO, user, exercise);

        userExerciseRepository.save(userExercise);

        return userExerciseMapper.toUserExerciseResponseDTO(userExercise);
    }

    public UserExerciseResponseDTO updateUserExercise(Long userExerciseId, UserExerciseDTO userExerciseDTO){
        UserExercise existingUserExercise = userExerciseRepository.findById(userExerciseId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserExercise with ID: " + userExerciseId + " not found"));

        User user = userRepository.findById(userExerciseDTO.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Exercise exercise = exerciseRepository.findById(userExerciseDTO.exerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));

        existingUserExercise.setUser(user);
        existingUserExercise.setExercise(exercise);
        existingUserExercise.setSets(userExerciseDTO.sets());
        existingUserExercise.setReps(userExerciseDTO.reps());
        existingUserExercise.setWeight(userExerciseDTO.weight());

        userExerciseRepository.save(existingUserExercise);

        return userExerciseMapper.toUserExerciseResponseDTO(existingUserExercise);
    }



    //find all exercises for user with particular ID
    public List<UserExerciseDetailsResponseDTO> getExercisesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no existing user with ID: " + userId));

        List<UserExercise> userExercises = userExerciseRepository.findByUser_UserId(userId);

        if(userExercises.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID: " + userId + " has no exercises.");
        }

        return userExercises.stream().map(userExerciseMapper::toUserExerciseDetailsResponseDTO).toList();
    }
}
