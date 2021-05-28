package org.generation.italy.universogames.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.generation.italy.universogames.security.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implementiamo UserDetails, l'interfaccia che offre SpringSecurity per avere
 * una gestione con permessi di livelli differenti
 * 
 * @author lucaf
 *
 */
@Entity
public class User implements UserDetails {

	private static final Map<String, Collection<? extends GrantedAuthority>> AUTHORITIES = new HashMap<>();

	{
		AUTHORITIES.put(Roles.ADMIN, Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("management"), }));
		AUTHORITIES.put(Roles.USER, Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_USER") }));

	}

	public static Collection<? extends GrantedAuthority> getAuthorityOfRole(String role) {
		return AUTHORITIES.get(role);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1237489217380966710L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String username;
	@Column(unique = true)
	private String password;
	@Column(unique = true)
	private String mail;
	private String ddn;
	private String role;

	public User(String username, String password, String mail, String ddn, String role) {
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.ddn = ddn;
		this.role = role;
	}

	public User() {
		super();
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * I permessi di ogni utente, può servire se gestiamo i livelli di accesso a
	 * seconda dei permessi di ogni utente Nel nostro caso ne faremo a meno, ma vi
	 * lascio un esempio di come si può implementare. Ci baseremo solamente sul
	 * ruolo dell'utente
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAuthorityOfRole(this.role);
	}
	

	/**
	 * A seconda se vi interessa avere certe funzionalità, implementate i campi
	 * appositi Questi sono richiesti dall'interfaccia. Per me valgono sempre true.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public String toString() {
		return "{username:" + username + ", password:" + password + ", mail:" + mail + ", ddn:" + ddn + ", role:" + role
				+ "}\n";
	}

}
