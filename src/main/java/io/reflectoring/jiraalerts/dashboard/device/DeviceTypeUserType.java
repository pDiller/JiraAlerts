package io.reflectoring.jiraalerts.dashboard.device;

import io.reflectoring.jiraalerts.common.EnumWithIdType;

/**
 * Type definition for mapping {@link DeviceType} to expected {@link org.hibernate.usertype.UserType}.
 */
public class DeviceTypeUserType extends EnumWithIdType<DeviceType> {

	@Override
	public Class<DeviceType> returnedClass() {
		return DeviceType.class;
	}
}
