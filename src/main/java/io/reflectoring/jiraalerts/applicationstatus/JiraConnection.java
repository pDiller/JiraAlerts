package io.reflectoring.jiraalerts.applicationstatus;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

/**
 * Entityobject for the connection to JIRA.
 */
@Entity
@Table(name = "JIRA_CONNECTION")
public class JiraConnection {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "USERNAME", nullable = false)
	private String username;

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
}
