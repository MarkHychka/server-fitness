package com.fitness.model;

import com.fitness.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Mark Hychka
 */
public class ExerciserSignUpModel {

    @NotNull
    @Size(min = 1, max = 30)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 30)
    private String lastName;
    @NotNull
    private Gender gender;
    @NotNull
    @Email(message = "wrongFormat")
    @Size(min = 5, max = 50)
    private String email;
    @NotNull
    @Size(min = 4, max = 20)
    private String password;

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
}
