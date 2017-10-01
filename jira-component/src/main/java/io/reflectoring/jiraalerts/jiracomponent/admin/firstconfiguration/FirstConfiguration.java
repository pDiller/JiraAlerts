package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "FIRST_CONFIGURATION")
public class FirstConfiguration {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "FIRST_CONFIGURATION")
	private boolean firstConfiguration;

	@Column(name = "CONFIGURED_AT")
	private Date configuredAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isFirstConfiguration() {
		return firstConfiguration;
	}

	public void setFirstConfiguration(boolean firstConfiguration) {
		this.firstConfiguration = firstConfiguration;
	}

	public Date getConfiguredAt() {
		return configuredAt;
	}

	public void setConfiguredAt(Date configuredAt) {
		this.configuredAt = configuredAt;
	}
}
