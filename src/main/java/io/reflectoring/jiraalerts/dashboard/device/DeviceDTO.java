package io.reflectoring.jiraalerts.dashboard.device;

import java.io.Serializable;

/**
 * Transferobject for {@link Device}
 */
public class DeviceDTO implements Serializable {

	private long id;

	private String name;

	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
