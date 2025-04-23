package com.example.FitnessTracker.UserExercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {
    List<UserExercise> findByUser_UserId(Long userId);
}
