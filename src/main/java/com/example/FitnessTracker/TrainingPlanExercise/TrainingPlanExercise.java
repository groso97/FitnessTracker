package com.example.FitnessTracker.TrainingPlanExercise;

import com.example.FitnessTracker.Exercise.Exercise;
import com.example.FitnessTracker.TrainingPlan.TrainingPlan;
import jakarta.persistence.*;

@Entity
@Table(name = "training_plan_exercises")
public class TrainingPlanExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_plan_id", nullable = false)
    private TrainingPlan trainingPlan;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private Integer sets;
    private Integer reps;

    public TrainingPlanExercise(){}

    public TrainingPlanExercise(TrainingPlan trainingPlan, Exercise exercise, Integer sets, Integer reps) {
        this.trainingPlan = trainingPlan;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingPlan getTrainingPlan() {
        return trainingPlan;
    }

    public void setTrainingPlan(TrainingPlan trainingPlan) {
        this.trainingPlan = trainingPlan;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
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
}
