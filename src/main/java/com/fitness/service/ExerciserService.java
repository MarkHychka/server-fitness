package com.fitness.service;

import com.fitness.dto.ExerciserDto;
import com.fitness.exception.DuplicateException;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.model.ExerciserUpdateModel;
import com.fitness.repository.ExerciserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
@Service
public class ExerciserService {

    @Autowired
    private ExerciserRepository exerciserRepository;

    @Transactional
    public ExerciserDto signUp(ExerciserSignUpModel model) throws DuplicateException {
        String email = model.getEmail();
        if (!exerciserRepository.findByEmail(email).isEmpty()) {
            throw new DuplicateException(String.format("Exerciser with email %s already exists", email));
        }
        UUID uuid = UUID.randomUUID();
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        exerciserRepository.insert(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender(),
                model.getPassword(), currentTime, currentTime, currentTime);
        return transform(model, uuid);
    }

    @Transactional(readOnly = true)
    public Long findIdByUuid(UUID exerciserUuid) throws NotFoundException {
        List<Long> id = exerciserRepository.findIdByUuid(exerciserUuid);
        if (id.isEmpty()) {
            throw new NotFoundException(String.format("Exerciser with uuid %s not found", exerciserUuid));
        }
        return id.get(0);
    }

    @Transactional
    public void update(UUID exerciserUuid, ExerciserUpdateModel model) throws NotFoundException {
        Long id = findIdByUuid(exerciserUuid);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        exerciserRepository.update(model.getFirstName(), model.getLastName(), model.getGender(), currentTime, id);
    }

    private ExerciserDto transform(ExerciserSignUpModel model, UUID uuid) {
        return new ExerciserDto(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender());
    }
}
