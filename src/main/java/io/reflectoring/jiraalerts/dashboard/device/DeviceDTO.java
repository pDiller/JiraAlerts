package io.reflectoring.jiraalerts.dashboard.device;

import java.io.Serializable;

/**
 * Transferobject for {@link Device}
 */
public class DeviceDTO implements Serializable {

	private long id;

	private String name;

	private String url;

	private DeviceType type;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}
}
