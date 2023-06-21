package com.criptologin.chatmessagewebsocket.registeruser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.criptologin.chatmessagewebsocket.repository.UserRepository;
import com.criptologin.chatmessagewebsocket.security.CustomUserDetails;
import com.criptologin.chatmessagewebsocket.tabelle.Role;
import com.criptologin.chatmessagewebsocket.tabelle.UserEntity;
import com.criptologin.chatmessagewebsocket.tabelle.UserRegister;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;	
	
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public UserService() {}
	// faccio la criptografia della password
	// (come? ottengo la password e poi la crittografo, salvando direttamente il valore nel db)
	// e poi salvo l'utente
	public UserEntity addUser(UserRegister userReg) {
		UserEntity user = new UserEntity(userReg.getEmail(), userReg.getPassword(), Arrays.asList(new Role("ROLE_USER")));
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// trovo l'oggetto utente ricercandolo tramite l'email che è univoca
				UserEntity user = userRepo.findByEmail(username);
		        
				if(user == null) {
					throw new UsernameNotFoundException("Usuario o password invalidi");
				}
				return new User(user.getEmail(),user.getPassword(), mapearAutoridadesRoles(user.getRoles()));
				
				
				// creo un oggetto per ogni dettaglio utente
				// + validazione e nel caso eccezione
				/*return user.map(CustomUserDetails::new)
		        		.orElseThrow(()->new UsernameNotFoundException("User not found" + username));*/
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
	}
	
	public List<UserEntity> listaUser() {
		return userRepo.findAll();
	}
}
