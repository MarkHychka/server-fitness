package com.fitness.controller;

import com.fitness.dto.WorkoutDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.WorkoutModel;
import com.fitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@RestController
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public void addWorkout(@PathVariable UUID exerciserUuid, @RequestBody WorkoutModel workoutModel) throws NotFoundException {
        workoutService.addWorkout(exerciserUuid, workoutModel);
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @workoutSecurity.hasPermission(authentication,#exerciserUuid,#workoutId)")
    public void updateWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId,
                              @RequestBody WorkoutModel workoutModel) throws NotFoundException {
        workoutService.updateWorkout(exerciserUuid, workoutId, workoutModel);
    }

    @DeleteMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @workoutSecurity.hasPermission(authentication,#exerciserUuid,#workoutId)")
    public void deleteWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId) throws NotFoundException {
        workoutService.deleteWorkout(exerciserUuid, workoutId);
    }

    @GetMapping(value = "/exerciser/{exerciserUuid}/workouts")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public List<WorkoutDto> findExerciserWorkouts(@PathVariable UUID exerciserUuid) throws NotFoundException {
        return workoutService.findExerciserWorkouts(exerciserUuid);
    }
}
