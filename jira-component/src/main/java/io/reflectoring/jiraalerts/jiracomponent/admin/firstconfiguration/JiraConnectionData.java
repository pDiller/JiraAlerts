package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "JIRA_CONNECTION_DATA")
public class JiraConnectionData {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "URL")
	private String url;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PW")
	private String pw;

	@Column(name = "MODIFIED_AT")
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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
