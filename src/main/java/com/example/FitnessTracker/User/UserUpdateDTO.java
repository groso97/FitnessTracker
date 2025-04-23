package com.example.FitnessTracker.User;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserUpdateDTO(
        @NotBlank(message = "First name cannot be blank")
        String firstName,

        @NotBlank(message = "Last name cannot be blank")
        String lastName,

        @Email(message = "Email should be valid")
        String email,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        @Pattern(regexp = "^(male|female)$", message = "Gender must be either 'male' or 'female'")
        String gender,

        @DecimalMin(value = "40.0", message = "Weight must be at least 40")
        @DecimalMax(value = "300.0", message = "Weight must be less than or equal to 300")
        Double weight,

        @DecimalMin(value = "120.0", message = "Height must be at least 120")
        @DecimalMax(value = "230.0", message = "Height must be less than or equal to 230")
        Double height
) {
}
