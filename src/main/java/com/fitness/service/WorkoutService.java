package com.fitness.service;

import com.fitness.dto.WorkoutDto;
import com.fitness.entity.Exerciser;
import com.fitness.entity.Workout;
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
import java.util.stream.Collectors;

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
                workoutModel.getDuration(), workoutModel.getWorkoutDate(), currentTime, currentTime);
    }

    @Transactional
    public void updateWorkout(UUID exerciserUuid, Long workoutId, WorkoutModel model) throws NotFoundException {
        exerciserService.findByUuid(exerciserUuid);
        Workout workout = workoutRepository.findById(workoutId);
        if (workout == null) {
            throw new NotFoundException(String.format("Workout with id %d not found", workoutId));
        }
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        workoutRepository.update(model.getType(), model.getCalories(), model.getDistance(), model.getDuration(), model.getWorkoutDate(), currentTime, workoutId);
    }

    @Transactional
    public void deleteWorkout(UUID exerciserUuid, Long workoutId) throws NotFoundException {
        exerciserService.findByUuid(exerciserUuid);
        workoutRepository.delete(workoutId);
    }

    @Transactional(readOnly = true)
    public  List<WorkoutDto> findExerciserWorkouts(UUID exerciserUuid) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        return workoutRepository.findByExerciserId(exerciser.getId())
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    private WorkoutDto transform(Workout workout) {
        return new WorkoutDto(
                workout.getId(),
                workout.getType(),
                workout.getCalories(),
                workout.getDistance(),
                workout.getDuration(),
                workout.getWorkoutDate()
        );
    }
}
