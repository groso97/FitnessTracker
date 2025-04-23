package com.example.FitnessTracker.Exercise;


import com.example.FitnessTracker.TrainingPlanExercise.TrainingPlanExercise;
import com.example.FitnessTracker.UserExercise.UserExercise;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;
    private String name;

    @Enumerated(EnumType.STRING)
    private ExerciseCategory category;
    private String description;

    @Enumerated(EnumType.STRING)
    private ExerciseEquipment equipment;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlanExercise> trainingPlanExercises;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserExercise> userExercises;


    public Exercise(){}

    public Exercise(String name, ExerciseCategory category, String description, ExerciseEquipment equipment, List<TrainingPlanExercise> trainingPlanExercises, List<UserExercise> userExercises) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.equipment = equipment;
        this.trainingPlanExercises = trainingPlanExercises;
        this.userExercises = userExercises;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(ExerciseEquipment equipment) {
        this.equipment = equipment;
    }

    public List<TrainingPlanExercise> getTrainingPlanExercises() {
        return trainingPlanExercises;
    }

    public void setTrainingPlanExercises(List<TrainingPlanExercise> trainingPlanExercises) {
        this.trainingPlanExercises = trainingPlanExercises;
    }

    public List<UserExercise> getUserExercises() {
        return userExercises;
    }

    public void setUserExercises(List<UserExercise> userExercises) {
        this.userExercises = userExercises;
    }
}
