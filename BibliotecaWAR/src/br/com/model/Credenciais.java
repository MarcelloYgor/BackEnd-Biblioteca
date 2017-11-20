package br.com.model;

import java.io.Serializable;

public class Credenciais implements Serializable {
	
	private static final long serialVersionUID = -5492575675950106543L;

	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
