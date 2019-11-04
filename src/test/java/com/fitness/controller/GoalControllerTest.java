package com.fitness.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.dto.GoalDto;
import com.fitness.model.GoalModel;
import com.fitness.repository.GoalRepository;
import com.fitness.service.GoalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GoalService goalService;

    @Test
    @WithMockUser(username = "edikfotul@gmail.com", password = "edikface15", roles = "EXERCISER")
    public void addGoalTest() throws Exception {
        GoalModel goalModel = new GoalModel();
        goalModel.setStartDate(Timestamp.valueOf("2019-09-16 15:00:00"));
        goalModel.setEndDate(Timestamp.valueOf("2019-09-17 15:00:00"));
        goalModel.setName("GoalThatNeverRepeat" + LocalDateTime.now());
        goalModel.setTarget(700d);
        goalModel.setGoalType("Swimming");

        mockMvc.perform(post("/exerciser/{exerciserUuid}/goal", "4711262d-dfed-4b48-8798-ffef4eebe2e8")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(goalModel)))
                .andExpect(status().isOk());

        List<GoalDto> goals = new ArrayList<>();
        goals.addAll(goalService.findExerciserGoals(UUID.fromString("4711262d-dfed-4b48-8798-ffef4eebe2e8")).stream()
                .filter(p -> p.getName().equals(goalModel.getName()))
                .collect(Collectors.toList()));
        assertThat(goals.get(0).getName()).isEqualTo(goalModel.getName());
    }
}
