package com.criptologin.chatmessagewebsocket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.criptologin.chatmessagewebsocket.registeruser.UserService;
import com.criptologin.chatmessagewebsocket.repository.UserRepository;
import com.criptologin.chatmessagewebsocket.tabelle.UserEntity;
import com.criptologin.chatmessagewebsocket.tabelle.UserRegister;

// con questi passaggi, è possibile utilizzare JPA per eseguire 
// operazioni CRUD (creazione, lettura, aggiornamento e cancellazione) 
// sul database tramite il repository JPA.
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index.html");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.html");
		return modelAndView;
	}
	
	// userRepo contiene la lista di tutti gli utenti salvati sul db
	public List<UserEntity> findAll(){
		return userRepo.findAll();
	}
	
	@ModelAttribute("user")
	public UserRegister nuovoRegistrato() {
		return new UserRegister();
	}
	
	@GetMapping("/new")
	public ModelAndView addNew() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("new.html");
		return modelAndView;
	}
	
	@PostMapping("/new")
	public RedirectView newUpdate(@ModelAttribute("user") UserRegister register) {
		String text = "";
		String email = register.getEmail();
		if(userRepo.findByEmail(email) != null) {
			text = "./new?esito";  
		}else {
			userService.addUser(register);
			text = "./new?success";  
		}
		return new RedirectView(text);      
	}
	
	/*@PostMapping("/login")
	public RedirectView newUpdate() {
		String email = register.getEmail();
		if(userRepo.findByEmail(email) != null) {
			text = "./new?esito";  
		}
		return new RedirectView("./login?error");    
	}*/
}

