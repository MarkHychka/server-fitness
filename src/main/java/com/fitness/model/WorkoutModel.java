package com.fitness.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Mark Hychka
 */
public class WorkoutModel {

    @NotNull
    @NotBlank
    private String type;
    private Integer calories;
    private Double distance;
    private Integer duration;
    @NotNull
    private Long workoutDate;

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

    public Long getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Long workoutDate) {
        this.workoutDate = workoutDate;
    }
}
