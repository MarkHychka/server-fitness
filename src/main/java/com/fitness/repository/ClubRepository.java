package com.fitness.repository;

import com.fitness.entity.Club;
import com.fitness.rowmapper.ClubRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Repository
public class ClubRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Club> getAllClubs() {
        return jdbcTemplate.query("SELECT * FROM club", new ClubRowMapper());
    }

    public List<Club> findFavorites(Long exercsierId) {
        return jdbcTemplate.query("SELECT c.* FROM club c " +
                "JOIN favorite_club fc ON fc.club_id = c.id " +
                "WHERE fc.exerciser_id = ?",
                new ClubRowMapper(),
                exercsierId);
    }

    public Club findByUuid(UUID uuid) {
        List<Club> result = jdbcTemplate.query("SELECT * FROM club WHERE uuid = ?",
                new ClubRowMapper(),
                uuid.toString());
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    public void addFavorite(Long exerciserId, Long clubId) {
        jdbcTemplate.update("INSERT INTO favorite_club VALUES (?, ?)",
                exerciserId,
                clubId);
    }
}
