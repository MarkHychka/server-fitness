package com.fitness.controller;

import com.fitness.dto.WorkoutDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.WorkoutModel;
import com.fitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Controller
@RequestMapping("/fitness")
@ComponentScan("com.fitness")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout")
    @ResponseStatus(HttpStatus.OK)
    public void addWorkout(@PathVariable UUID exerciserUuid, @RequestBody @Valid WorkoutModel workoutModel) throws NotFoundException {
        workoutService.addWorkout(exerciserUuid, workoutModel);
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId,
                              @RequestBody @Valid WorkoutModel workoutModel) throws NotFoundException {
        workoutService.updateWorkout(exerciserUuid, workoutId, workoutModel);
    }

    @DeleteMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId) throws NotFoundException {
        workoutService.deleteWorkout(exerciserUuid, workoutId);
    }

    @GetMapping(value = "/exerciser/{exerciserUuid}/workouts")
    public @ResponseBody List<WorkoutDto> findExerciserWorkouts(@PathVariable UUID exerciserUuid) throws NotFoundException {
        return workoutService.findExerciserWorkoutsByUuid(exerciserUuid);
    }

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}/delete")
    public String deleteWorkoutAndRedirectToProfile(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId) throws NotFoundException {
        workoutService.deleteWorkout(exerciserUuid, workoutId);
        return "redirect:/fitness/profile";
    }

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout/{workoutId}/update")
    public String updateWorkoutAndRedirectToProfile(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId,
                                                    @ModelAttribute @Valid WorkoutModel workoutModel, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return "updateWorkout";
        }
        workoutService.updateWorkout(exerciserUuid, workoutId, workoutModel);
        return "redirect:/fitness/profile";
    }

    @PostMapping(value = "/exerciser/{exerciserUuid}/workout/add")
    public String addWorkoutAndRedirectToProfile(@PathVariable UUID exerciserUuid, @ModelAttribute @Valid WorkoutModel workoutModel,
                                                 BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return "addWorkout";
        }
        workoutService.addWorkout(exerciserUuid, workoutModel);
        return "redirect:/fitness/profile";
    }
}
