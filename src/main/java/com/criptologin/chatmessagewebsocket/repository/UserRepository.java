package com.criptologin.chatmessagewebsocket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.criptologin.chatmessagewebsocket.tabelle.UserEntity;

// interfaccia che fornisce operazioni comuni (CRUD)
// ed implementazione sarà generata in fase di esecuzione da Spring Data JPA
// MI SERVE PER IMPOSTARE LA SEMPLICE AUTORIZZAZIONE
// questa interfaccia mi funge da repository dei dati utente 
// e da qui posso acquisire tutti i dati possibili
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	// la colonna email è univoca nella tabella e mi serve per 
	// autenticare il login dell'utente
    UserEntity findByEmail(String email);

}
