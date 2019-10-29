package com.fitness.configuration;

import com.fitness.entity.Exerciser;
import com.fitness.entity.Goal;
import com.fitness.exception.NotFoundException;
import com.fitness.repository.GoalRepository;
import com.fitness.service.ExerciserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GoalSecurity {

    @Autowired
    ExerciserSecurity exerciserSecurity;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    ExerciserService exerciserService;

    public boolean hasPermission(Authentication authentication, UUID exerciserUuid, Long goalId) throws NotFoundException {
        exerciserSecurity.hasPermission(authentication, exerciserUuid);
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        Goal goal = goalRepository.findById(goalId);
        return goal.getExerciserId().equals(exerciser.getId());
    }
}
