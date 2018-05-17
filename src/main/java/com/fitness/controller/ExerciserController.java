package com.fitness.controller;

import com.fitness.dto.ExerciserDto;
import com.fitness.exception.DuplicateException;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignInModel;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.model.ExerciserUpdateModel;
import com.fitness.service.ExerciserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

/**
 * @author Mark Hychka
 */
@Controller
@RequestMapping("/fitness")
@ComponentScan("com.fitness")
public class ExerciserController {

    @Autowired
    private ExerciserService exerciserService;

    @PostMapping(value = "/signUp")
    public @ResponseBody ExerciserDto signUp(@RequestBody @Valid ExerciserSignUpModel exerciserSignUpModel) throws DuplicateException, NotFoundException {
        return exerciserService.signUp(exerciserSignUpModel);
    }

    @PostMapping(value = "/login")
    public @ResponseBody ExerciserDto login() throws NotFoundException {
        return exerciserService.getExerciserProfile(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping(value = "/signIn")
    public String signIn(@ModelAttribute @Valid ExerciserSignInModel exerciserSignInModel, BindingResult bindingResult, Model model) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return "signIn";
        }
        ExerciserDto exerciserDto = exerciserService.signIn(exerciserSignInModel.getEmail(), exerciserSignInModel.getPassword());
        model.addAttribute("exerciserDto", exerciserDto);
        return "profile";
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID exerciserUuid,
                       @ModelAttribute @Valid ExerciserUpdateModel exerciserUpdateModel) throws NotFoundException {
        exerciserService.update(exerciserUuid, exerciserUpdateModel);
    }

    @PostMapping(value = "/logOut")
    @ResponseStatus(HttpStatus.OK)
    public void logOut() {
        ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession().invalidate();
    }

    @GetMapping(value = "/exercisers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<ExerciserDto> showExercisers() {
        return exerciserService.showExercisers();
    }

    @DeleteMapping(value = "/exerciser/{exerciserUuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExerciser(@PathVariable UUID exerciserUuid) throws NotFoundException {
        exerciserService.delete(exerciserUuid);
    }
}