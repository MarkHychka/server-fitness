package com.fitness.controller;

import com.fitness.exception.NotFoundException;
import com.fitness.model.WorkoutModel;
import com.fitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Mark Hychka
 */
@RestController
@RequestMapping("/fitness")
@ComponentScan("com.fitness")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout")
    @ResponseStatus(HttpStatus.OK)
    public void addWorkout(@PathVariable UUID exerciserUuid, @RequestBody WorkoutModel workoutModel) throws NotFoundException {
        workoutService.addWorkout(exerciserUuid, workoutModel);
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}/workout")
    @ResponseStatus(HttpStatus.OK)
    public void updateWorkout(@PathVariable UUID exerciserUuid, @RequestBody WorkoutModel workoutModel)
}
