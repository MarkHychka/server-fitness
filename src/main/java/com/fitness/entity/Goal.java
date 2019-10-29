package com.fitness.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

public class Goal {

    private Long id;
    private Long exerciserId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String name;
    private Double target;
    private String goalType;
    private Double progress;
    private Boolean completed;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Goal(Long id, Long exerciserId, Timestamp startDate, Timestamp endDate, String name, Double target, String goalType, Double progress, Boolean completed, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.exerciserId = exerciserId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.target = target;
        this.goalType = goalType;
        this.progress = progress;
        this.completed = completed;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
