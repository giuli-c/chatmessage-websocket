package com.criptologin.chatmessagewebsocket.tabelle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.criptologin.chatmessagewebsocket.*;
import com.criptologin.chatmessagewebsocket.repository.UserRepository;

/*@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired // mi serve per inserire @Beans quando dichiarati
	// non serve scrivere get o set davanti al nome
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
    
    @Test
    public void testCreateUser() {
    	UserEntity user = new UserEntity();
    	user.setEmail("Giulia");
    	user.setPassword("giulia");
    	
    	UserEntity userSaved = repo.save(user);
    	
    	UserEntity userExist = entityManager.find(UserEntity.class, userSaved.getId());
    	
    	assertThat(user.getPassword()).isEqualTo(userExist.getPassword());
    }
    
}*/
