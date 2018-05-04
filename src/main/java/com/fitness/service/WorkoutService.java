package com.fitness.service;

import com.fitness.exception.NotFoundException;
import com.fitness.model.WorkoutModel;
import com.fitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciserService exerciserService;

    @Transactional
    public void addWorkout(UUID exerciserUuid, WorkoutModel workoutModel) throws NotFoundException {
        Long exerciserId = exerciserService.findIdByUuid(exerciserUuid);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        workoutRepository.insert(exerciserId, workoutModel.getType(), workoutModel.getCalories(), workoutModel.getDistance(),
                workoutModel.getDuration(), currentTime, currentTime);
    }
}
