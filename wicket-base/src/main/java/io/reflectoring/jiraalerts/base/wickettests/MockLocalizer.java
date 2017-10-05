package io.reflectoring.jiraalerts.base.wickettests;

import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.Localizer;
import org.apache.wicket.model.IModel;

public class MockLocalizer extends Localizer {

	@Override
	public String getString(String key, Component component) {
		return key;
	}

	@Override
	public String getString(String key, Component component, String defaultValue) {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model) {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model, String defaultValue) {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model, Locale locale, String style, String defaultValue) {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model, Locale locale, String style, IModel<String> defaultValue) {
		return key;
	}

}
