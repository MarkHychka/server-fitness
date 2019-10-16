package com.fitness.controller;

import com.fitness.dto.ClubDto;
import com.fitness.exception.NotFoundException;
import com.fitness.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@RestController
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping(value = "/exerciser/{exerciserUuid}/clubs")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public List<ClubDto> clubsForExerciser(@PathVariable UUID exerciserUuid) throws NotFoundException {
        return clubService.clubsForExerciser(exerciserUuid);
    }

    @PostMapping(value = "/exerciser/{exerciserUuid}/club/{clubUuid}/favorite-club")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public void addFavoriteClub(@PathVariable UUID exerciserUuid, @PathVariable UUID clubUuid) throws NotFoundException {
        clubService.addFavoriteClub(exerciserUuid, clubUuid);
    }

    @DeleteMapping(value = "/exerciser/{exerciserUuid}/club/{clubUuid}/favorite-club")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public void removeFavoriteClub(@PathVariable UUID exerciserUuid, @PathVariable UUID clubUuid) throws NotFoundException {
        clubService.removeFavoriteClub(exerciserUuid, clubUuid);
    }
}
