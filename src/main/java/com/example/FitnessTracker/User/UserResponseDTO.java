package com.example.FitnessTracker.User;

import java.time.LocalDate;

public record UserResponseDTO(
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        String gender,
        double weight,
        double height
) {
}
