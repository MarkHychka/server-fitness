package com.fitness.rowmapper;

import com.fitness.Gender;
import com.fitness.dto.ExerciserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
public class ExerciserRowMapper implements RowMapper<ExerciserDto> {

    @Override
    public ExerciserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ExerciserDto(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                UUID.fromString(rs.getString("uuid")),
                Gender.valueOf(rs.getString("gender"))
        );
    }
}