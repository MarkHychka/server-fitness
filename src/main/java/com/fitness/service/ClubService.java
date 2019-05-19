package com.fitness.service;

import com.fitness.dto.ClubDto;
import com.fitness.entity.Club;
import com.fitness.entity.Exerciser;
import com.fitness.exception.NotFoundException;
import com.fitness.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Mark Hychka
 */
@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private ExerciserService exerciserService;

    @Transactional(readOnly = true)
    public List<ClubDto> allClubs() {
        return clubRepository.getAllClubs()
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    private ClubDto transform(Club club) {
        return new ClubDto(
                club.getUuid(),
                club.getName(),
                club.getLatitude(),
                club.getLongitude()
        );
    }

    @Transactional(readOnly = true)
    public List<ClubDto> favoriteClubs(UUID exerciserUuid) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        return clubRepository.findFavorites(exerciser.getId())
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addFavoriteClub(UUID exerciserUuid, UUID clubUuid) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        Club club = clubRepository.findByUuid(clubUuid);
        if (club == null) {
            throw new NotFoundException(String.format("Club with uuid %s not found", clubUuid));
        }
        clubRepository.addFavorite(exerciser.getId(), club.getId());
    }
}
