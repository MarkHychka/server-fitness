package com.fitness.repository;

import com.fitness.Gender;
import com.fitness.dto.ExerciserDto;
import com.fitness.rowmapper.ExerciserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Repository
public class ExerciserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ExerciserDto> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM exerciser WHERE email = ?", new ExerciserRowMapper(), email);
    }

    public void insert(String firstName, String lastName, String email, UUID uuid, Gender gender, String password) {
        jdbcTemplate.update("INSERT INTO exerciser (uuid, email, password, first_name, last_name, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?)",
                uuid.toString(),
                email,
                password,
                firstName,
                lastName,
                gender.toString());
    }
}
