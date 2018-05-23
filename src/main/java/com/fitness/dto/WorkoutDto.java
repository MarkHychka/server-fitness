package com.fitness.dto;

/**
 * @author Mark Hychka
 */
public class WorkoutDto {

    private Long id;
    private String type;
    private Integer calories;
    private Double distance;
    private Integer duration;
    private Long workoutDate;

    public WorkoutDto() {
    }

    public WorkoutDto(Long id, String type, Integer calories, Double distance, Integer duration, Long workoutDate) {
        this.id = id;
        this.type = type;
        this.calories = calories;
        this.distance = distance;
        this.duration = duration;
        this.workoutDate = workoutDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Long workoutDate) {
        this.workoutDate = workoutDate;
    }
}
