package org.generation.italy.universogames.auth;

import java.util.Optional;

import org.generation.italy.universogames.model.User;
import org.generation.italy.universogames.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//Implementiamo UserDetailsService, l'interfaccia di SpringSecurity 
//che utilizza come oggetto per andare a controllare gli utenti attraverso lo username
public class AuthService implements UserDetailsService {

	private UserRepository dao;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public AuthService(UserRepository dao, PasswordEncoder passwordEncoder) {
		this.dao = dao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<? extends UserDetails> user = dao.findByUsername(username);

		if (user.isPresent())
			return user.get();

		throw new UsernameNotFoundException("Nessun utente con username " + username);
	}

	public void signup(String mail, String username, String password, String ddn) {
		User newUser = new User();
		newUser.setMail(mail);
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setDdn(ddn);
		newUser.setRole(Roles.USER);
		try {			
			dao.save(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
