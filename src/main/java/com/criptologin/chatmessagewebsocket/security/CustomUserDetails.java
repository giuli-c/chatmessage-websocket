package com.criptologin.chatmessagewebsocket.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.criptologin.chatmessagewebsocket.tabelle.UserEntity;

// i metodi di questa classe sono richiamati da spring security
// durante il processo di autenticazione
// per dire a spring security come cercare le infromazioni utente
// è necessario codificare la classe "userDetailService"
public class CustomUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private List<GrantedAuthority> autorities;
	
	// passo le info utente per mappare i dati di questa classe con quelle dell'utente
	// il costruttore mi serve per il metodo "UserDetails loadUserByUsername(String username)",
	// della classe "CustomUserDetailService" che mi serve per fare ritornare 
	// un oggetto "UserDetail" e quindi devo convertire le info utente in dettagli utente
	public CustomUserDetails(UserEntity userEntity) {
		email = userEntity.getEmail();
		password = userEntity.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

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

}
