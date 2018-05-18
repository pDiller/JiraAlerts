package io.reflectoring.jiraalerts.dashboard.device;

import java.util.Locale;
import java.util.ResourceBundle;

import io.reflectoring.jiraalerts.common.EnumWithId;
import io.reflectoring.jiraalerts.common.LocalizedEnum;

/**
 * The type of a device.
 */
public enum DeviceType implements EnumWithId, LocalizedEnum {

	RAITO4RPI(0), //
	PHILIPS_HUE(1);

	private final int id;

	DeviceType(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toLocalizedString() {
		return ResourceBundle.getBundle(getClass().getName(), Locale.getDefault()).getString(name());
	}
}
