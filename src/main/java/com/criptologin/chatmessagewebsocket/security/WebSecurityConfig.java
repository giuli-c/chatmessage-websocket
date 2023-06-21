package com.criptologin.chatmessagewebsocket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.criptologin.chatmessagewebsocket.registeruser.UserService;

// QUESTA CLASSE MI SERVE PER RECUPERARE LE INFORMAZIONI DAL DB
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	// creo un contenitore di UserDetailService per definire le cose
	// le cose relative all'autenticazione (dettagli utente quali nome e password)
		
	// VIENE RESTITUITO UN OGGETTO CUSTOMUSERDETAILSERVICE, CONVERTENDO LE INFO UTENTE
	// IN DETTAGLI UTENTE CHE PASSERRANNO POI SUCCESSIVAMENTE ALL'AUTENTICAZIONE
	@Bean
	public UserDetailsService userDetailService() {
		return new UserService();
	}
	
			
		// configuro l'autorizzazione
		// mi assicura che qualsiasi richiesta all'applicazione sia autenticata
		// con accesso basato basato su modulo (form di login automatica)
		  // Configuro pagina di accesso predefinita (login generato da spring)
		  // con il nome del parametro del nome utente (email) > /users
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
	                .authorizeHttpRequests()
	                .antMatchers("/chatmessage/**").permitAll()
	                .antMatchers("/webjars/**").permitAll()
	                .and()
	                .logout()
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/login?logout")
	                .and()
	                .formLogin(form -> form
	                		.loginPage("/login").permitAll())
	                .build();
	}
	
	// creo codificatore di password
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	   public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	 }
}
