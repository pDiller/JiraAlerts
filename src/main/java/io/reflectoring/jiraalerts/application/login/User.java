package io.reflectoring.jiraalerts.application.login;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entityobject for the user.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "SALT", nullable = false)
	private String salt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
