package com.example.FitnessTracker.TrainingPlan;

import com.example.FitnessTracker.TrainingPlanExercise.TrainingPlanExercise;
import com.example.FitnessTracker.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "training_plans")
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingPlanId;
    private String name;
    private String description;
    private Integer durationWeeks;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlanExercise> exercises;


    public TrainingPlan(){}

    public TrainingPlan(String name, String description, Integer durationWeeks, LocalDate createdAt, User user, List<TrainingPlanExercise> exercises) {
        this.name = name;
        this.description = description;
        this.durationWeeks = durationWeeks;
        this.createdAt = createdAt;
        this.user = user;
        this.exercises = exercises;
    }

    public Long getTrainingPlanId() {
        return trainingPlanId;
    }

    public void setTrainingPlanId(Long trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(Integer durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TrainingPlanExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<TrainingPlanExercise> exercises) {
        this.exercises = exercises;
    }
}
