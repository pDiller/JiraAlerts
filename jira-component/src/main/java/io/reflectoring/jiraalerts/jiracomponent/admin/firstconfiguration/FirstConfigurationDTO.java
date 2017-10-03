package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import java.util.Date;

public class FirstConfigurationDTO {

	private long id;

	private Date configuredAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getConfiguredAt() {
		return configuredAt;
	}

	public void setConfiguredAt(Date configuredAt) {
		this.configuredAt = configuredAt;
	}
}
