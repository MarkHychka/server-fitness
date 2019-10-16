package com.fitness.configuration;

import com.fitness.entity.Exerciser;
import com.fitness.entity.Workout;
import com.fitness.exception.NotFoundException;
import com.fitness.repository.WorkoutRepository;
import com.fitness.service.ExerciserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WorkoutSecurity {

    @Autowired
    ExerciserSecurity exerciserSecurity;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    ExerciserService exerciserService;

    public boolean hasPermission(Authentication authentication, UUID exerciserUuid, Long workoutId) throws NotFoundException {
        exerciserSecurity.hasPermission(authentication, exerciserUuid);
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        Workout workout = workoutRepository.findById(workoutId);
        return workout.getExerciserId().equals(exerciser.getId());
    }
}
