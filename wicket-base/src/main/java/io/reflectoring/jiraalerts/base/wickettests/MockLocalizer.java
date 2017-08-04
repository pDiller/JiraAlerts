package io.reflectoring.jiraalerts.base.wickettests;

import java.util.Locale;
import java.util.MissingResourceException;

import org.apache.wicket.Component;
import org.apache.wicket.Localizer;
import org.apache.wicket.model.IModel;

public class MockLocalizer extends Localizer {

	@Override
	public String getString(String key, Component component) throws MissingResourceException {
		return key;
	}

	@Override
	public String getString(String key, Component component, String defaultValue) throws MissingResourceException {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model) throws MissingResourceException {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model, String defaultValue) throws MissingResourceException {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model, Locale locale, String style, String defaultValue)
	        throws MissingResourceException {
		return key;
	}

	@Override
	public String getString(String key, Component component, IModel<?> model, Locale locale, String style, IModel<String> defaultValue)
	        throws MissingResourceException {
		return key;
	}

}
