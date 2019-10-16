package com.fitness.service;

import com.fitness.dto.ExerciserDto;
import com.fitness.entity.Exerciser;
import com.fitness.exception.DuplicateException;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.model.ExerciserUpdateModel;
import com.fitness.repository.ExerciserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fitness.RoleType.ROLE_EXERCISER;

/**
 * @author Mark Hychka
 */
@Service
public class ExerciserService {

    @Autowired
    private ExerciserRepository exerciserRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ExerciserDto signUp(ExerciserSignUpModel model) throws DuplicateException, NotFoundException {
        String email = model.getEmail();
        if (exerciserRepository.findByEmail(email) != null) {
            throw new DuplicateException(String.format("Exerciser with email %s already exists", email));
        }
        UUID uuid = UUID.randomUUID();
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        String encodedPassword = passwordEncoder.encode(model.getPassword());
        exerciserRepository.insert(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender(),
                encodedPassword, currentTime, currentTime, currentTime);
        authenticate(uuid, email, encodedPassword);
        return transform(model, uuid);
    }

    private void authenticate(UUID exerciserUuid, String email, String encodedPassword) throws NotFoundException {
        Long exerciserId = findByUuid(exerciserUuid).getId();
        List<SimpleGrantedAuthority> authorities = roleService.findRolesByExerciserId(exerciserId).stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(ROLE_EXERCISER.name()));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, encodedPassword, authorities));
    }

    @Transactional(readOnly = true)
    public Exerciser findByUuid(UUID exerciserUuid) throws NotFoundException {
        Exerciser exerciser = exerciserRepository.findByUuid(exerciserUuid);
        if (exerciser == null) {
            throw new NotFoundException(String.format("Exerciser with uuid %s not found", exerciserUuid));
        }
        return exerciser;
    }

    @Transactional
    public void update(UUID exerciserUuid, ExerciserUpdateModel model) throws NotFoundException {
        Exerciser exerciser = findByUuid(exerciserUuid);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        exerciserRepository.update(model.getFirstName(), model.getLastName(), model.getGender(), currentTime, exerciser.getId());
    }

    @Transactional(readOnly = true)
    public Exerciser findByEmail(String email) {
        return exerciserRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public ExerciserDto getExerciserProfile(String email) {
        return transform(exerciserRepository.findByEmail(email));
    }

    private ExerciserDto transform(ExerciserSignUpModel model, UUID uuid) {
        return new ExerciserDto(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender());
    }

    private ExerciserDto transform(Exerciser exerciser) {
        return new ExerciserDto(exerciser.getFirstName(), exerciser.getLastName(), exerciser.getEmail(), exerciser.getUuid(), exerciser.getGender());
    }
}
