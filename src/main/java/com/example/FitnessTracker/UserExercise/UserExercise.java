package com.example.FitnessTracker.UserExercise;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.User.User;
import jakarta.persistence.*;


@Entity
@Table(name = "user_exercises")
public class UserExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userExerciseId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private Integer sets;
    private Integer reps;
    private Double weight;


    public UserExercise(){}

    public UserExercise(User user, Exercise exercise, Integer sets, Integer reps, Double weight) {
        this.user = user;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public Long getUserExerciseId() {
        return userExerciseId;
    }

    public void setUserExerciseId(Long userExerciseId) {
        this.userExerciseId = userExerciseId;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }


}
