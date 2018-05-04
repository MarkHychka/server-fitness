package com.fitness;

import com.fitness.controller.ExerciserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnessApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ExerciserController.class, args);
    }
}
