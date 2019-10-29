package com.fitness.controller;

import com.fitness.dto.GoalDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.GoalModel;
import com.fitness.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping(value = "/exerciser/{exerciserUuid}/goal")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public void addGoal(@PathVariable UUID exerciserUuid, @ModelAttribute GoalModel goalModel) throws NotFoundException {
        goalService.addGoal(exerciserUuid, goalModel);
    }

    @PutMapping(value = "/exerciser/{exerciserUuid}/goal/{goalId}")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @goalSecurity.hasPermission(authentication,#exerciserUuid,#goalId)")
    public void updateWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long goalId,
                              @ModelAttribute GoalModel goalModel) throws NotFoundException {
        goalService.updateGoal(exerciserUuid, goalId, goalModel);
    }

    @DeleteMapping(value = "/exerciser/{exerciserUuid}/goal/{goalId}")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @goalSecurity.hasPermission(authentication,#exerciserUuid,#goalId)")
    public void deleteWorkout(@PathVariable UUID exerciserUuid, @PathVariable Long goalId) throws NotFoundException {
        goalService.deleteGoal(exerciserUuid, goalId);
    }

    @GetMapping(value = "/exerciser/{exerciserUuid}/goals")
    @PreAuthorize("hasRole('ROLE_EXERCISER') and @exerciserSecurity.hasPermission(authentication,#exerciserUuid)")
    public List<GoalDto> findExerciserGoals(@PathVariable UUID exerciserUuid) throws NotFoundException {
        return goalService.findExerciserGoals(exerciserUuid);
    }
}
