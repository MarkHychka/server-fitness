package com.fitness.rowmapper;

import com.fitness.Gender;
import com.fitness.entity.Exerciser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
public class ExerciserRowMapper implements RowMapper<Exerciser> {

    @Override
    public Exerciser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Exerciser(
                rs.getLong("id"),
                UUID.fromString(rs.getString("uuid")),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                Gender.valueOf(rs.getString("gender")),
                new Timestamp(rs.getLong("created_at")),
                new Timestamp(rs.getLong("updated_at")),
                new Timestamp(rs.getLong("last_login_time"))
        );
    }
}