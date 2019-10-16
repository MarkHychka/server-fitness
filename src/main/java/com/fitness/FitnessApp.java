package com.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class FitnessApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FitnessApp.class, args);
    }
}
