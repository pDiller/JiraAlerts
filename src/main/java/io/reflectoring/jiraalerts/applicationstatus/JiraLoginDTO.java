package io.reflectoring.jiraalerts.applicationstatus;

import java.io.Serializable;

/**
 * Transferobject for JIRA-connection values.
 */
public class JiraLoginDTO implements Serializable {

	private String Url;

	private String username;

	private String password;

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

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
