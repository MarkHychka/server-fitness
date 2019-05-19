package com.fitness.rowmapper;

import com.fitness.entity.Club;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
public class ClubRowMapper implements RowMapper<Club> {

    @Nullable
    @Override
    public Club mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Club(
                rs.getLong("id"),
                UUID.fromString(rs.getString("uuid")),
                rs.getString("name"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getTimestamp("created_at")
        );
    }
}
