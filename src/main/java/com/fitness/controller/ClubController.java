package com.fitness.controller;

import com.fitness.dto.ClubDto;
import com.fitness.exception.NotFoundException;
import com.fitness.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@RestController
@RequestMapping("/fitness")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping(value = "/clubs")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public List<ClubDto> allClubs() {
        return clubService.allClubs();
    }

    @GetMapping(value = "/exerciser/{exerciserUuid}/favorite-clubs")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public List<ClubDto> favoriteClubs(@PathVariable UUID exerciserUuid) throws NotFoundException {
        return clubService.favoriteClubs(exerciserUuid);
    }

    @PostMapping(value = "/exerciser/{exerciserUuid}/club/{clubUuid}/favorite-club")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public void addFavoriteClub(@PathVariable UUID exerciserUuid, @PathVariable UUID clubUuid) throws NotFoundException {
        clubService.addFavoriteClub(exerciserUuid, clubUuid);
    }
}