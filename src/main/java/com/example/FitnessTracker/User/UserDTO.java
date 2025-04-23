package com.example.FitnessTracker.User;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserDTO(
        @NotBlank(message = "First name cannot be blank.")
        String firstName,
        @NotBlank(message = "Last name cannot be blank.")
        String lastName,
        @Pattern(regexp = "^[a-zA-Z]+(\\.[a-zA-Z]+)*@[a-zA-Z]+\\.[a-zA-Z]+$", message = "Invalid email format")
        String email,
        @Past
        LocalDate birthDate,
        @NotBlank(message = "Gender cannot be blank.")
        @Pattern(regexp = "^(male|female)$", message = "Gender must be either 'male' or 'female'")
        String gender,

        @NotNull(message = "Weight cannot be null")
        @DecimalMin(value = "40.0", message = "Weight must be at least 40")
        @DecimalMax(value = "300.0", message = "Weight must be less than or equal to 300")
        double weight,

        @NotNull(message = "Height cannot be null")
        @DecimalMin(value = "120.0", message = "Height must be at least than 120")
        @DecimalMax(value = "230.0", message = "Height must be less than or equal to 230")
        double height
) { }
