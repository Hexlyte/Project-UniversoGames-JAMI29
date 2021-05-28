package org.generation.italy.universogames.controller;

import org.generation.italy.universogames.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
	@Autowired
	private AuthService authService;

	@PostMapping
	public String signup(@RequestParam String mail, @RequestParam String username, @RequestParam String password, @RequestParam String ddn) {
		authService.signup(mail, username, password, ddn);
		return "Ok, registrato.";
	}
}