package com.fitness.service;

import com.fitness.dto.WorkoutDto;
import com.fitness.entity.Exerciser;
import com.fitness.exception.NotFoundException;
import com.fitness.model.WorkoutModel;
import com.fitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciserService exerciserService;

    @Transactional
    public void addWorkout(UUID exerciserUuid, WorkoutModel workoutModel) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        workoutRepository.insert(exerciser.getId(), workoutModel.getType(), workoutModel.getCalories(), workoutModel.getDistance(),
                workoutModel.getDuration(), new Timestamp(workoutModel.getWorkoutDate()), currentTime, currentTime);
    }

    @Transactional
    public void updateWorkout(UUID exerciserUuid, Long workoutId, WorkoutModel model) throws NotFoundException {
        exerciserService.findByUuid(exerciserUuid);
        List<WorkoutDto> workout = workoutRepository.findById(workoutId);
        if (workout.isEmpty()) {
            throw new NotFoundException(String.format("Workout with id %d not found", workoutId));
        }
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        workoutRepository.update(model.getType(), model.getCalories(), model.getDistance(), model.getDuration(), new Timestamp(model.getWorkoutDate()), currentTime, workoutId);
    }

    @Transactional
    public void deleteWorkout(UUID exerciserUuid, Long workoutId) throws NotFoundException {
        exerciserService.findByUuid(exerciserUuid);
        workoutRepository.delete(workoutId);
    }

    @Transactional(readOnly = true)
    public  List<WorkoutDto> findExerciserWorkoutsByUuid(UUID exerciserUuid) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        return workoutRepository.findByExerciserId(exerciser.getId());
    }

    @Transactional
    public WorkoutDto findByWorkoutId(UUID exerciserUuid, Long workoutId) throws NotFoundException {
        exerciserService.findByUuid(exerciserUuid);
        List<WorkoutDto> workoutDtos = workoutRepository.find(workoutId);
        if(workoutDtos.isEmpty()) {
            throw new NotFoundException(String.format("Workout with id %d not found", workoutId));
        }
        return workoutDtos.get(0);
    }

    @Transactional(readOnly = true)
    public  List<WorkoutDto> findExerciserWorkoutsById(Long exerciserId) {
        return workoutRepository.findByExerciserId(exerciserId);
    }
}
