package com.fitness.controller;

import com.fitness.dto.ExerciserDto;
import com.fitness.dto.WorkoutDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignInModel;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.model.ExerciserUpdateModel;
import com.fitness.model.WorkoutModel;
import com.fitness.service.ExerciserService;
import com.fitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Controller
@SessionAttributes("exerciserDto")
public class HtmlPagesController {

    @Autowired
    private ExerciserService exerciserService;

    @Autowired
    private WorkoutService workoutService;

    @GetMapping(value = "/fitness/signIn")
    public String getLoginPage(ExerciserSignInModel exerciserSignInModel) {
        return "signIn";
    }

    @GetMapping(value = "/fitness/register")
    public String getSignUpPage(ExerciserSignUpModel exerciserSignUpModel) {
        return "register";
    }

    @GetMapping(value = "/fitness")
    public String getFitnessPage() {
        return "fitness";
    }

    @GetMapping(value = "/fitness/profile")
    public String getProfilePage(Model model) throws NotFoundException {
        Map<String, Object> map = model.asMap();
        ExerciserDto exerciserDto =(ExerciserDto) map.get("exerciserDto");
        UUID exerciserUuid = exerciserDto.getUuid();
        ExerciserDto dto = exerciserService.findDtoByUuid(exerciserUuid);
        List<WorkoutDto> workouts = workoutService.findExerciserWorkoutsByUuid(exerciserUuid);
        model.addAttribute("exerciserDto", dto);
        model.addAttribute("workouts", workouts);
        return "profile";
    }

    @GetMapping(value = "/fitness/exerciser/{exerciserUuid}/workout/{workoutId}/update")
    public String getUpdateWorkoutPage(@PathVariable UUID exerciserUuid, @PathVariable Long workoutId, Model model) throws NotFoundException {
        WorkoutDto workoutDto = workoutService.findByWorkoutId(exerciserUuid, workoutId);
        model.addAttribute("workoutModel", createWorkoutModel(workoutDto));
        model.addAttribute("workoutId", workoutDto.getId());
        model.addAttribute("exerciserUuid", exerciserUuid);
        return "updateWorkout";
    }

    @GetMapping(value = "/fitness/exerciser/update")
    public String getExerciserUpdatePage(Model model) {
        Map<String, Object> map = model.asMap();
        ExerciserDto exerciserDto =(ExerciserDto) map.get("exerciserDto");
        ExerciserUpdateModel exerciserUpdateModel = createExerciserUpdateModel(exerciserDto);
        model.addAttribute("exerciserUpdateModel", exerciserUpdateModel);
        return "updateProfile";
    }

    @GetMapping(value = "/fitness/exerciser/{exerciserUuid}/workout/add")
    public String getAddWorkoutPage(@PathVariable UUID exerciserUuid, Model model) throws NotFoundException {
        model.addAttribute("workoutModel", new WorkoutModel());
        model.addAttribute("exerciserUuid", exerciserUuid);
        return "addWorkout";
    }

    private ExerciserUpdateModel createExerciserUpdateModel(ExerciserDto exerciserDto) {
        ExerciserUpdateModel exerciserUpdateModel = new ExerciserUpdateModel();
        exerciserUpdateModel.setFirstName(exerciserDto.getFirstName());
        exerciserUpdateModel.setLastName(exerciserDto.getLastName());
        exerciserUpdateModel.setGender(exerciserDto.getGender());
        return exerciserUpdateModel;
    }

    private WorkoutModel createWorkoutModel(WorkoutDto workoutDto) {
        WorkoutModel workoutModel = new WorkoutModel();
        workoutModel.setCalories(workoutDto.getCalories());
        workoutModel.setDuration(workoutDto.getDuration());
        workoutModel.setDistance(workoutDto.getDistance());
        workoutModel.setType(workoutDto.getType());
        workoutModel.setWorkoutDate(workoutDto.getWorkoutDate());
        return workoutModel;
    }
}
