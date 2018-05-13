package com.fitness.repository;

import com.fitness.entity.Role;
import com.fitness.rowmapper.RoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fitness.Role.ROLE_EXERCISER;

/**
 * @author Mark Hychka
 */
@Repository
public class RoleRepository {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public List<Role> findRolesByExerciserId(Long exerciserId) {
        return jdbcTemplate.query("SELECT r.* FROM role r " +
                "JOIN exerciser_role er ON er.role_id = r.id WHERE er.exerciser_id = ?", new RoleRowMapper(), exerciserId);
    }

    public void createExerciserRole(Long exerciserId, Long roleId) {
        jdbcTemplate.update("INSERT INTO exerciser_role VALUES (?, ?)", exerciserId, roleId);
    }

    public List<Role> getExerciserRoleId() {
        return jdbcTemplate.query("SELECT * FROM role WHERE name = ?", new RoleRowMapper(), ROLE_EXERCISER.toString());
    }
}
