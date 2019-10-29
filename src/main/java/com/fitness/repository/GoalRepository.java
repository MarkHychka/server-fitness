package com.fitness.repository;

import com.fitness.entity.Goal;
import com.fitness.rowmapper.GoalRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class GoalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Long exerciserId, Timestamp startDate, Timestamp endDate, String name,
                       Double target, String goalType, Timestamp createdAt, Timestamp updatedAt) {
        jdbcTemplate.update("INSERT INTO goal(exerciser_id, start_date, end_date, name, target, goal_type, progress, completed, created_at, updated_at)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?)",
                exerciserId,
                startDate,
                endDate,
                name,
                target,
                goalType,
                0,
                false,
                createdAt,
                updatedAt);

    }

    public void update(Timestamp startDate, Timestamp endDate, String name, Double target, String goalType, Timestamp updatedAt, Long goalId) {
        jdbcTemplate.update("UPDATE goal SET start_date = ?, end_date = ?, name = ?, target = ?, goal_type = ?, updated_at = ? WHERE id = ?",
                startDate,
                endDate,
                name,
                target,
                goalType,
                updatedAt,
                goalId);
    }

    public Goal findById(Long goalId) {
        List<Goal> result = jdbcTemplate.query("SELECT * FROM goal WHERE id = ?", new GoalRowMapper(), goalId);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    public void deleteGoal(Long goalId) {
        jdbcTemplate.update("DELETE FROM goal WHERE id = ?", goalId);
    }

    public List<Goal> findExerciserGoals(Long exerciserId) {
        return jdbcTemplate.query("SELECT * FROM goal WHERE exerciser_id = ?", new GoalRowMapper(), exerciserId);
    }
}
