package com.fitness.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

/**
 * @author Mark Hychka
 */
public class WorkoutDto {

    private Long workoutId;
    private String type;
    private Integer calories;
    private Double distance;
    private Integer duration;
    private Timestamp workoutDate;

    public WorkoutDto() {
    }

    public WorkoutDto(Long workoutId, String type, Integer calories, Double distance, Integer duration, Timestamp workoutDate) {
        this.workoutId = workoutId;
        this.type = type;
        this.calories = calories;
        this.distance = distance;
        this.duration = duration;
        this.workoutDate = workoutDate;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
