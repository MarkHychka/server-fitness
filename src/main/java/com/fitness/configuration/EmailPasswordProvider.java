package com.fitness.configuration;

import com.fitness.entity.Exerciser;
import com.fitness.exception.NotFoundException;
import com.fitness.service.ExerciserService;
import com.fitness.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.fitness.Role.ROLE_EXERCISER;

/**
 * @author Mark Hychka
 */
@Component
public class EmailPasswordProvider implements AuthenticationProvider {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ExerciserService exerciserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = passwordEncoder.encode(authentication.getCredentials().toString());
        List<Exerciser> exerciserList = exerciserService.findByEmailAndPassword(login, password);
        if (exerciserList.isEmpty()) {
            throw new BadCredentialsException("Exerciser not found");
        }
        List<SimpleGrantedAuthority> authorities = roleService.findRolesByExerciserId(exerciserList.get(0).getId()).stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(ROLE_EXERCISER.name()));
        return new UsernamePasswordAuthenticationToken(login, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
