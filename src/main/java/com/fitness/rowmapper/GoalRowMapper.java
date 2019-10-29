package com.fitness.rowmapper;

import com.fitness.entity.Goal;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalRowMapper implements RowMapper<Goal> {

    @Nullable
    @Override
    public Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Goal(
                rs.getLong("id"),
                rs.getLong("exerciser_Id"),
                rs.getTimestamp("start_date"),
                rs.getTimestamp("end_date"),
                rs.getString("name"),
                rs.getDouble("target"),
                rs.getString("goal_type"),
                rs.getDouble("progress"),
                rs.getBoolean("completed"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        );
    }
}
