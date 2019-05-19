package com.fitness.repository;

import com.fitness.Gender;
import com.fitness.entity.Exerciser;
import com.fitness.rowmapper.ExerciserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Repository
public class ExerciserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Exerciser> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM exerciser WHERE email = ?",
                new ExerciserRowMapper(),
                email);
    }

    public void insert(String firstName, String lastName, String email, UUID uuid, Gender gender, String password,
                       Timestamp createdAt, Timestamp updatedAt, Timestamp lastLoginTime) {
        jdbcTemplate.update("INSERT INTO exerciser (uuid, email, password, first_name, " +
                        "last_name, gender, created_at, updated_at, last_login_time) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                uuid.toString(),
                email,
                password,
                firstName,
                lastName,
                gender.toString(),
                createdAt,
                updatedAt,
                lastLoginTime);
    }

    public Exerciser findByUuid(UUID uuid) {
        List<Exerciser> result = jdbcTemplate.query("SELECT * FROM exerciser WHERE uuid = ?",
                new ExerciserRowMapper(),
                uuid.toString());
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    public void update(String firstName, String lastName, Gender gender, Timestamp updatedAt, Long id) {
        jdbcTemplate.update("UPDATE exerciser SET first_name = ?, last_name = ?, gender = ?, updated_at = ? WHERE id = ?",
            firstName,
            lastName,
            gender.toString(),
            updatedAt,
            id);
    }
}
