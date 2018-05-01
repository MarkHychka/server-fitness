package com.fitness.controller;

import com.fitness.dto.ExerciserDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.service.ExerciserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mark Hychka
 */
@RestController
@RequestMapping("/fitness")
@ComponentScan("com.fitness")
@SpringBootApplication
public class SimpleController {

    @Autowired
    private ExerciserService exerciserService;

    @PostMapping(value = "/signUp")
    public ExerciserDto signUp(@RequestBody @Valid ExerciserSignUpModel exerciserSignUpModel) throws NotFoundException {
        return exerciserService.signUp(exerciserSignUpModel);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleController.class, args);
    }
}