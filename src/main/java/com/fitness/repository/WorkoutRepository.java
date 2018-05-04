package com.fitness.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class WorkoutRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Long exerciserId, String type, int calories, double distance, double duration, Timestamp createdAt, Timestamp updatedAt) {
        jdbcTemplate.update("INSERT INTO workout (exerciser_id, type, calories, distance, duration, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",
                exerciserId,
                type,
                calories,
                distance,
                duration,
                createdAt,
                updatedAt);
    }
}
