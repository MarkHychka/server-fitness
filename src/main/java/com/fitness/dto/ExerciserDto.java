package com.fitness.dto;

import com.fitness.Gender;

import java.util.UUID;

/**
 * @author Mark Hychka
 */
public class ExerciserDto {

    private String firstName;
    private String lastName;
    private String email;
    private UUID uuid;
    private Gender gender;

    public ExerciserDto(String firstName, String lastName, String email, UUID uuid, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.uuid = uuid;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
