package io.reflectoring.jiraalerts.login;

import java.io.Serializable;

/**
 * Transferobject for logindata.
 */
class LoginDTO implements Serializable {

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
