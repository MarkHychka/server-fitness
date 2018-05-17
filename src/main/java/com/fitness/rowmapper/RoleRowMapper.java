package com.fitness.rowmapper;

import com.fitness.RoleType;
import com.fitness.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mark Hychka
 */
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Role(
                rs.getLong("id"),
                RoleType.valueOf(rs.getString("name")));
    }
}
