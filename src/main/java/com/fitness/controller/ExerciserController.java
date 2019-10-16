package com.fitness.controller;

import com.fitness.dto.ExerciserDto;
import com.fitness.exception.DuplicateException;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.model.ExerciserUpdateModel;
import com.fitness.service.ExerciserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@RestController
public class ExerciserController {

    @Autowired
    private ExerciserService exerciserService;

    @PostMapping(value = "/signUp")
    public ExerciserDto signUp(@ModelAttribute @Valid ExerciserSignUpModel exerciserSignUpModel) throws DuplicateException, NotFoundException {
        return exerciserService.signUp(exerciserSignUpModel);
    }

    @PostMapping(value = "/login")
    public ExerciserDto login() throws NotFoundException {
        return exerciserService.getExerciserProfile(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public void update(@PathVariable UUID exerciserUuid,
                       @ModelAttribute @Valid ExerciserUpdateModel exerciserUpdateModel) throws NotFoundException {
        exerciserService.update(exerciserUuid, exerciserUpdateModel);
    }
}