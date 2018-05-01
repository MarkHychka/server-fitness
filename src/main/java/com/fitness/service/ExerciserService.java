package com.fitness.service;

import com.fitness.dto.ExerciserDto;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.repository.ExerciserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Service
public class ExerciserService {

    @Autowired
    private ExerciserRepository exerciserRepository;

    public ExerciserDto signUp(ExerciserSignUpModel model) throws NotFoundException {
        String email = model.getEmail();
        if (!exerciserRepository.findByEmail(email).isEmpty()) {
            throw new NotFoundException(String.format("Exerciser with email %s already exists", email));
        }
        UUID uuid = UUID.randomUUID();
        exerciserRepository.insert(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender(), model.getPassword());
        return transform(model, uuid);
    }

    private ExerciserDto transform(ExerciserSignUpModel model, UUID uuid) {
        return new ExerciserDto(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender());
    }
}
