package com.fitness.rowmapper;

import com.fitness.entity.Workout;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mark Hychka
 */
public class WorkoutRowMapper implements RowMapper<Workout> {

    @Nullable
    @Override
    public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Workout(
                rs.getLong("id"),
                rs.getLong("exerciser_id"),
                rs.getString("type"),
                (Integer) rs.getObject("calories"),
                (Double) rs.getObject("distance"),
                (Integer) rs.getObject("duration"),
                rs.getTimestamp("workout_date"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        );
    }
}
