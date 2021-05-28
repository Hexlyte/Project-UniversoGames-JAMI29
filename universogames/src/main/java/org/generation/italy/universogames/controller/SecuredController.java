package org.generation.italy.universogames.controller;

import org.generation.italy.universogames.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("secured")
public class SecuredController {

    @GetMapping
    public User detail(@AuthenticationPrincipal User user) {
        return user;
    }
}
