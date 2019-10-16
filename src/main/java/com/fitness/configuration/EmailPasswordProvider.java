package com.fitness.configuration;

import com.fitness.entity.Exerciser;
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

import static com.fitness.RoleType.ROLE_EXERCISER;

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
        String rawPassword = authentication.getCredentials().toString();
        Exerciser exerciser = exerciserService.findByEmail(login);
        if (exerciser == null) {
            throw new BadCredentialsException("Exerciser not found");
        }
        String encodedPassword = exerciser.getPassword();
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new BadCredentialsException("Wrong password");
        }
        List<SimpleGrantedAuthority> authorities = roleService.findRolesByExerciserId(exerciser.getId()).stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(ROLE_EXERCISER.name()));
        return new UsernamePasswordAuthenticationToken(login, rawPassword, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
