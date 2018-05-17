package com.fitness.controller;

import com.fitness.model.ExerciserSignInModel;
import com.fitness.model.ExerciserSignUpModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mark Hychka
 */
@Controller
public class HtmlPagesController {

    @GetMapping(value = "/fitness/signIn")
    public String getLoginPage(ExerciserSignInModel exerciserSignInModel) {
        return "signIn";
    }

    @GetMapping(value = "/fitness/signUp")
    public String getSignUpPage(ExerciserSignUpModel exerciserSignUpModel) {
        return "signUp";
    }

    @GetMapping(value = "/fitness")
    public String getFitnessPage() {
        return "fitness";
    }

    @GetMapping(value = "/fitness/profile")
    public String getProfilePage() {
        return "profile";
    }
}
