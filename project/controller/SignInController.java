package com.xworkz.project.controller;

import com.xworkz.project.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")

public class SignInController {
    @Autowired
    private SignInService service;

    @PostMapping("/signin")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {

        boolean isValid = service.signIn(email, password);

        if (isValid) {
            model.addAttribute("message", "Login successful!");
            model.addAttribute("email", email); // this is passed to the profile page
            return "profile";
        } else {
            model.addAttribute("message", "Invalid email or password.");
            return "signIn";
        }
    }


}
