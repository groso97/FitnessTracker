package com.example.FitnessTracker.User;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    public UserRepository userRepository;
    public UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> findAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no users in the database.");
        }

        return users.stream().map(userMapper::toUserResponseDTO).collect(Collectors.toList());
    }

    public UserResponseDTO findUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There is no existing user with ID: " + userId));

        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO createUser(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDTO(savedUser);
    }

    public String deleteUserById(Long userId){
        if(!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID: " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);

        return "User with ID " + userId + " was successfully deleted.";
    }

    public UserResponseDTO updateUser(Long userId, UserUpdateDTO userUpdateDTO){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(userUpdateDTO.firstName() != null) user.setFirstName(userUpdateDTO.firstName());
        if(userUpdateDTO.lastName() != null) user.setLastName(userUpdateDTO.lastName());
        if(userUpdateDTO.email() != null) user.setEmail(userUpdateDTO.email());
        if(userUpdateDTO.birthDate() != null) user.setBirthDate(userUpdateDTO.birthDate());
        if(userUpdateDTO.gender() != null) user.setGender(userUpdateDTO.gender());
        if(userUpdateDTO.weight() != null) user.setWeight(userUpdateDTO.weight());
        if(userUpdateDTO.height() != null) user.setHeight(userUpdateDTO.height());

        userRepository.save(user);

        return userMapper.toUserResponseDTO(user);
    }
}
