package com.fitness.rowmapper;

import com.fitness.dto.WorkoutDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mark Hychka
 */
public class WorkoutRowMapper implements RowMapper<WorkoutDto> {
    @Override
    public WorkoutDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WorkoutDto(
                rs.getLong("id"),
                rs.getString("type"),
                (Integer) rs.getObject("calories"),
                (Double) rs.getObject("distance"),
                (Integer) rs.getObject("duration"),
                rs.getTimestamp("workout_date")
        );
    }
}
