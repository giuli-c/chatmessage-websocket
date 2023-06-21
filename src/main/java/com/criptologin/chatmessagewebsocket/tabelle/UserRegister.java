package com.criptologin.chatmessagewebsocket.tabelle;

public class UserRegister {
	
	private String email;	
	private String password;	
	
	public UserRegister(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserRegister() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
