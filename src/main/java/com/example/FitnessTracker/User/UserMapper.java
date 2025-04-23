package com.example.FitnessTracker.User;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toEntity(UserDTO userDTO){
        return new User(
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.email(),
                userDTO.birthDate(),
                userDTO.gender(),
                userDTO.weight(),
                userDTO.height(),
                null,
                null
        );
    }

    public UserResponseDTO toUserResponseDTO(User user){
        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getGender(),
                user.getWeight(),
                user.getHeight()
        );

    }
}
