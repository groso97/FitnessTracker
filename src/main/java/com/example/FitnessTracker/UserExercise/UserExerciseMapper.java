package com.example.FitnessTracker.UserExercise;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.User.User;
import org.springframework.stereotype.Service;


@Service
public class UserExerciseMapper {
    public UserExercise toEntity(UserExerciseDTO userExerciseDTO, User user, Exercise exercise){
        return new UserExercise(
                user,
                exercise,
                userExerciseDTO.sets(),
                userExerciseDTO.reps(),
                userExerciseDTO.weight()
        );
    }


    public UserExerciseDetailsResponseDTO toUserExerciseDetailsResponseDTO(UserExercise userExercise){
        return new UserExerciseDetailsResponseDTO(
                userExercise.getExercise().getName(),
                userExercise.getExercise().getCategory().name(),
                userExercise.getExercise().getDescription(),
                userExercise.getExercise().getEquipment().name(),
                userExercise.getSets(),
                userExercise.getReps(),
                userExercise.getWeight()
        );
    }

    public UserExerciseResponseDTO toUserExerciseResponseDTO(UserExercise userExercise){
        return new UserExerciseResponseDTO(
                userExercise.getUserExerciseId(),
                userExercise.getUser().getUserId(),
                userExercise.getExercise().getExerciseId(),
                userExercise.getSets(),
                userExercise.getReps(),
                userExercise.getWeight()

        );
    }
}
