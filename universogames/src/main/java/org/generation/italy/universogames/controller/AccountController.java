package org.generation.italy.universogames.controller;

import org.generation.italy.universogames.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accountmanager")
public class AccountController {
	
	@GetMapping
	public String get() {
		return "<h1>Benvenuto, account manager!</h1>";
	}

	@GetMapping("/detail")
	public User test(@AuthenticationPrincipal User user) {
		return user;
	}
}
