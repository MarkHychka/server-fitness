package com.fitness.service;

import com.fitness.dto.GoalDto;
import com.fitness.entity.Exerciser;
import com.fitness.entity.Goal;
import com.fitness.exception.NotFoundException;
import com.fitness.model.GoalModel;
import com.fitness.repository.GoalRepository;
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
public class GoalService {

    @Autowired
    private ExerciserService exerciserService;

    @Autowired
    private GoalRepository goalRepository;

    @Transactional
    public void addGoal(UUID exerciserUuid, GoalModel goalModel) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        goalRepository.insert(exerciser.getId(), goalModel.getStartDate(), goalModel.getEndDate(),
                goalModel.getName(), goalModel.getTarget(), goalModel.getGoalType(), currentTime, currentTime);
    }

    @Transactional
    public void updateGoal(UUID exerciserUuid, Long goalId, GoalModel goalModel) throws NotFoundException {
        Goal goal = goalRepository.findById(goalId);
        if (goal == null) {
            throw new NotFoundException(String.format("Goal with id %d not found", goalId));
        }
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        goalRepository.update(goalModel.getStartDate(), goalModel.getEndDate(), goalModel.getName(), goalModel.getTarget(),
                goalModel.getGoalType(), currentTime, goalId);
    }

    @Transactional
    public void deleteGoal(UUID exerciserUuid, Long goalId) throws NotFoundException {
        Goal goal = goalRepository.findById(goalId);
        if (goal == null) {
            throw new NotFoundException(String.format("Goal with id %d not found", goalId));
        }
        goalRepository.deleteGoal(goalId);
    }

    @Transactional
    public List<GoalDto> findExerciserGoals(UUID exerciserUuid) throws NotFoundException {
        Exerciser exerciser = exerciserService.findByUuid(exerciserUuid);
        return goalRepository.findExerciserGoals(exerciser.getId())
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    private GoalDto transform(Goal goal) {
        return new GoalDto(
                goal.getId(),
                goal.getStartDate(),
                goal.getEndDate(),
                goal.getName(),
                goal.getTarget(),
                goal.getGoalType(),
                goal.getProgress(),
                goal.getCompleted()
        );
    }
}
