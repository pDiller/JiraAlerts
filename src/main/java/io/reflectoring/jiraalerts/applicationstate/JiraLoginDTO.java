package io.reflectoring.jiraalerts.applicationstate;

import java.io.Serializable;

/**
 * Transferobject for JIRA-connection values.
 */
public class JiraLoginDTO implements Serializable {

	private String url;

	private String username;

	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
