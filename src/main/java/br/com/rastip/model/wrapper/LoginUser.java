package br.com.rastip.model.wrapper;

import java.io.Serializable;

public class LoginUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username, password;
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
