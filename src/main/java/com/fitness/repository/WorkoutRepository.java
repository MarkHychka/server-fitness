package com.fitness.repository;

import com.fitness.dto.WorkoutDto;
import com.fitness.entity.Workout;
import com.fitness.rowmapper.WorkoutRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class WorkoutRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Long exerciserId, String type, Integer calories, Double distance, Integer duration,
                       Timestamp workoutDate, Timestamp createdAt, Timestamp updatedAt) {
        jdbcTemplate.update("INSERT INTO workout (exerciser_id, type, calories, distance, duration, workout_date, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                exerciserId,
                type,
                calories,
                distance,
                duration,
                workoutDate,
                createdAt,
                updatedAt);
    }

    public void update(String type, Integer calories, Double distance, Integer duration, Timestamp workoutDate, Timestamp updatedAt, Long workoutId) {
        jdbcTemplate.update("UPDATE workout SET type = ?, calories = ?, distance = ?, duration = ?, workout_date = ?, updated_at = ? WHERE id = ?",
                type,
                calories,
                distance,
                duration,
                workoutDate,
                updatedAt,
                workoutId);
    }

    public Workout findById(Long id) {
        List<Workout> result = jdbcTemplate.query("SELECT * FROM workout WHERE id = ?", new WorkoutRowMapper(), id);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM workout WHERE id = ?", id);
    }

    public List<Workout> findByExerciserId(Long exerciserId) {
        return jdbcTemplate.query("SELECT * FROM workout WHERE exerciser_id = ?", new WorkoutRowMapper(), exerciserId);
    }
}
