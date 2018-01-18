package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.markup.html.form.EnumChoiceRenderer;

/**
 * ChoiceRenderer to display the values of {@link DeviceType} according to the enum's property files.
 */
public class DeviceTypeChoiceRenderer extends EnumChoiceRenderer<DeviceType> {

	@Override
	public Object getDisplayValue(DeviceType deviceType) {
		return deviceType.toLocalizedString();
	}

	@Override
	public String getIdValue(DeviceType deviceType, int index) {
		return deviceType.name();
	}

}
