package com.fitness.configuration;

import com.fitness.repository.ExerciserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExerciserSecurity {

    @Autowired
    ExerciserRepository exerciserRepository;

    public boolean hasPermission(Authentication authentication, UUID exerciserUuid) {
        UUID uuid = exerciserRepository.findByEmail(authentication.getName()).getUuid();
        return uuid.equals(exerciserUuid);
    }
}
