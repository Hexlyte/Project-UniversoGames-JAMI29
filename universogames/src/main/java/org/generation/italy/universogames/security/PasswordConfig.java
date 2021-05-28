package org.generation.italy.universogames.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// SpringSecurity ci obbliga a criptare le password. Qui definiamo quale sarà la crittografia utilizzata da noi
public class PasswordConfig {
	
	// bean funziona come repository, service. Un bean è un oggetto che verrà fornito con autowired se serve
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// Uno delle tante criptografie esistenti
	}
}