package com.fitness.service;

import com.fitness.dto.GoalDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.GoalModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoalServiceTest {

    @Autowired
    private GoalService goalService;

    @Test
    public void updateGoalTest() throws Exception {
        GoalModel goalModel = new GoalModel();
        goalModel.setStartDate(Timestamp.valueOf("2019-09-16 15:00:00"));
        goalModel.setEndDate(Timestamp.valueOf("2019-09-17 15:00:00"));
        goalModel.setName("Changed name" + LocalDateTime.now());
        goalModel.setTarget(700d);
        goalModel.setGoalType("Swimming");

        goalService.updateGoal(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8"), 3L, goalModel);

        GoalDto goalDto = getGoalDto(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8"), goalModel);
        assertThat(goalDto.getName()).isEqualTo(goalModel.getName());
    }

    @Test
    public void deleteGoalTest() throws Exception {
        GoalModel goalModel = new GoalModel();
        goalModel.setStartDate(Timestamp.valueOf("2019-09-16 15:00:00"));
        goalModel.setEndDate(Timestamp.valueOf("2019-09-17 15:00:00"));
        goalModel.setName("New Goal" + LocalDateTime.now());
        goalModel.setTarget(700d);
        goalModel.setGoalType("Swimming");

        goalService.addGoal(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8"), goalModel);
        GoalDto goalDto = getGoalDto(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8"), goalModel);
        goalService.deleteGoal(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8"), goalDto.getId());

        List<GoalDto> deletedGoal = goalService.findExerciserGoals(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8")).stream()
                .filter(p -> p.getName().equals(goalModel.getName()))
                .collect(Collectors.toList());
        assertThat(deletedGoal.size()).isEqualTo(0);
    }

    private GoalDto getGoalDto(UUID exerciserUuid, GoalModel goalModel) throws NotFoundException {
        List<GoalDto> goals = goalService.findExerciserGoals(exerciserUuid).stream()
                .filter(p -> p.getName().equals(goalModel.getName()))
                .collect(Collectors.toList());
        return goals.get(0);
    }
}
