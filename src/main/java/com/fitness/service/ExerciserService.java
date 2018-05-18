package com.fitness.service;

import com.fitness.dto.ExerciserDto;
import com.fitness.dto.WorkoutDto;
import com.fitness.entity.Exerciser;
import com.fitness.exception.DuplicateException;
import com.fitness.exception.NotFoundException;
import com.fitness.model.ExerciserSignUpModel;
import com.fitness.model.ExerciserUpdateModel;
import com.fitness.repository.ExerciserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
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
        if (!exerciserRepository.findByEmail(email).isEmpty()) {
            throw new DuplicateException(String.format("Exerciser with email %s already exists", email));
        }
        UUID uuid = UUID.randomUUID();
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        String encodedPassword = passwordEncoder.encode(model.getPassword());
        exerciserRepository.insert(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender(),
                encodedPassword, currentTime, currentTime, currentTime);
        Long exerciserId = findByUuid(uuid).getId();
        roleService.createExerciserRole(exerciserId);
        List<SimpleGrantedAuthority> authorities = roleService.findRolesByExerciserId(exerciserId).stream()
                .map(role -> new SimpleGrantedAuthority(role.getType().toString()))
                .collect(Collectors.toList());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, model.getPassword(), authorities));
        return transform(model, uuid);
    }

    @Transactional(readOnly = true)
    public Exerciser findByUuid(UUID exerciserUuid) throws NotFoundException {
        List<Exerciser> exerciser = exerciserRepository.findByUuid(exerciserUuid);
        if (exerciser.isEmpty()) {
            throw new NotFoundException(String.format("Exerciser with uuid %s not found", exerciserUuid));
        }
        return exerciser.get(0);
    }

    @Transactional(readOnly = true)
    public ExerciserDto signIn(String email, String rawPassword) {
        List<Exerciser> exerciserList = findByEmail(email);
        if (exerciserList.isEmpty()) {
            throw new BadCredentialsException("Exerciser not found");
        }
        Exerciser exerciser = exerciserList.get(0);
        String encodedPassword = exerciser.getPassword();
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new BadCredentialsException("Wrong password");
        }
        List<SimpleGrantedAuthority> authorities = roleService.findRolesByExerciserId(exerciser.getId()).stream()
                .map(role -> new SimpleGrantedAuthority(role.getType().toString()))
                .collect(Collectors.toList());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, rawPassword, authorities));
        return transform(exerciser);
    }

    @Transactional
    public void update(UUID exerciserUuid, ExerciserUpdateModel model) throws NotFoundException {
        Exerciser exerciser = findByUuid(exerciserUuid);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        exerciserRepository.update(model.getFirstName(), model.getLastName(), model.getGender(), currentTime, exerciser.getId());
    }

    @Transactional(readOnly = true)
    public List<Exerciser> findByEmail(String email) {
        return exerciserRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public ExerciserDto getExerciserProfile(String email) {
        return transform(exerciserRepository.findByEmail(email).get(0));
    }

    private ExerciserDto transform(ExerciserSignUpModel model, UUID uuid) {
        return new ExerciserDto(model.getFirstName(), model.getLastName(), model.getEmail(), uuid, model.getGender());
    }

    public static ExerciserDto transform(Exerciser exerciser) {
        return new ExerciserDto(exerciser.getFirstName(), exerciser.getLastName(), exerciser.getEmail(), exerciser.getUuid(), exerciser.getGender());
    }

    @Transactional(readOnly = true)
    public List<ExerciserDto> showExercisers() {
        return exerciserRepository.findExercisers().stream()
                .filter(exerciser -> !roleService.isAdmin(exerciser.getId()))
                .map(ExerciserService::transform)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(UUID exerciserUuid) throws NotFoundException {
        Exerciser exerciser = findByUuid(exerciserUuid);
        exerciserRepository.delete(exerciser.getId());
    }
}
