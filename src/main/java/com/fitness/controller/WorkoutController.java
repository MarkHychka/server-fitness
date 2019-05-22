package com.fitness.controller;

import com.fitness.dto.WorkoutDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.WorkoutModel;
import com.fitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@RestController
@RequestMapping("/fitness")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public void addWorkout(@PathVariable UUID exerciserUuid, @ModelAttribute WorkoutModel workoutModel) throws NotFoundException {
        workoutService.addWorkout(exerciserUuid, workoutModel);
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public void updateWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId,
                              @ModelAttribute WorkoutModel workoutModel) throws NotFoundException {
        workoutService.updateWorkout(exerciserUuid, workoutId, workoutModel);
    }

    @DeleteMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public void deleteWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId) throws NotFoundException {
        workoutService.deleteWorkout(exerciserUuid, workoutId);
    }

    @GetMapping(value = "/exerciser/{exerciserUuid}/workouts")
    @PreAuthorize("hasRole('ROLE_EXERCISER')")
    public List<WorkoutDto> findExerciserWorkouts(@PathVariable UUID exerciserUuid) throws NotFoundException {
        return workoutService.findExerciserWorkouts(exerciserUuid);
    }
}
