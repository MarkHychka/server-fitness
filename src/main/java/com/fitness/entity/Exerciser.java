package com.fitness.entity;

import com.fitness.Gender;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Mark Hychka
 */
public class Exerciser {

    private Long id;
    private UUID uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp lastLoginTime;

    public Exerciser(Long id, UUID uuid, String email, String password, String firstName, String lastName,
                     Gender gender, Timestamp createdAt, Timestamp updatedAt, Timestamp lastLoginTime) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginTime = lastLoginTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
