package com.fitness.entity;

import java.sql.Timestamp;

/**
 * @author Mark Hychka
 */
public class Workout {

    private Long id;
    private Long exerciserId;
    private String type;
    private Integer calories;
    private Double distance;
    private Integer duration;
    private Timestamp workoutDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Workout(Long id, Long exerciserId, String type, Integer calories, Double distance,
                   Integer duration, Timestamp workoutDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.exerciserId = exerciserId;
        this.type = type;
        this.calories = calories;
        this.distance = distance;
        this.duration = duration;
        this.workoutDate = workoutDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciserId() {
        return exerciserId;
    }

    public void setExerciserId(Long exerciserId) {
        this.exerciserId = exerciserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Timestamp workoutDate) {
        this.workoutDate = workoutDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
