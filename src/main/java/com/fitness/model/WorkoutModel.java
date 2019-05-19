package com.fitness.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Mark Hychka
 */
public class WorkoutModel {

    @NotNull
    private String type;
    private Integer calories;
    private Double distance;
    private Integer duration;
    @NotNull
    private Timestamp workoutDate;

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
