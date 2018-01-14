package io.reflectoring.jiraalerts.dashboard.device;

import io.reflectoring.jiraalerts.common.EnumWithId;

/**
 * The type of a device.
 */
public enum DeviceType implements EnumWithId {

	RAITO4RPI(0), PHILIPS_HUE(1);

	private final int id;

	DeviceType(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
}
