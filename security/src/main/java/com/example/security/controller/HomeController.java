package com.example.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping
    public String getHome(Authentication authentication) {
        if (authentication != null) {
            log.info("{} called home...", authentication.getName());
        } else {
            log.info("guest called home...");
        }


        return "home";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "logout";
    }
}
