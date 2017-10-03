package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import java.io.Serializable;
import java.util.Date;

public class JiraConnectionDataDTO implements Serializable {

	private long id;

	private String url;

	private String username;

	private Date modifiedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}