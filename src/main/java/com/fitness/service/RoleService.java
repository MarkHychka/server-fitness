package com.fitness.service;

import com.fitness.entity.Role;
import com.fitness.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mark Hychka
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Role> findRolesByExerciserId(Long exerciserId) {
        return roleRepository.findRolesByExerciserId(exerciserId);
    }

    @Transactional
    public void createExerciserRole(Long exerciserId) {
        roleRepository.createExerciserRole(exerciserId, getExerciserRoleId());
    }

    @Transactional(readOnly = true)
    public Long getExerciserRoleId() {
        return roleRepository.getExerciserRoleId().get(0).getId();
    }
}
