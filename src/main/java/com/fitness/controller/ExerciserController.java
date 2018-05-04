package com.fitness.controller;

import com.fitness.dto.ExerciserDto;
import com.fitness.exception.DuplicateException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.service.ExerciserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mark Hychka
 */
@RestController
@RequestMapping("/fitness/exerciser")
@ComponentScan("com.fitness")
public class ExerciserController {

    @Autowired
    private ExerciserService exerciserService;

    @PostMapping(value = "/signUp")
    @ResponseStatus(HttpStatus.OK)
    public ExerciserDto signUp(@RequestBody @Valid ExerciserSignUpModel exerciserSignUpModel) throws DuplicateException {
        return exerciserService.signUp(exerciserSignUpModel);
    }
}