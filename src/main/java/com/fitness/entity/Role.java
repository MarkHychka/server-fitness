package com.fitness.entity;

import com.fitness.RoleType;

/**
 * @author Mark Hychka
 */
public class Role {

    private Long id;
    private RoleType type;

    public Role(Long id, RoleType type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
