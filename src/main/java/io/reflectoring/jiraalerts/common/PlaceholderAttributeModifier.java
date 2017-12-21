package io.reflectoring.jiraalerts.common;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.IModel;

/**
 * Adds the placeholder-attribute to the given component.
 */
public class PlaceholderAttributeModifier extends AttributeModifier {

	private static final String PLACEHOLDER_ATTRIBUTE = "placeholder";

	/**
	 * Constructor.
	 *
	 * @param placeholderValueModel
	 *            Value for the placeholder.
	 */
	public PlaceholderAttributeModifier(IModel<?> placeholderValueModel) {
		super(PLACEHOLDER_ATTRIBUTE, placeholderValueModel);
	}

	/**
	 * Constructor.
	 *
	 * @param placeholderValue
	 *            Value for the placeholder.
	 */
	public PlaceholderAttributeModifier(Serializable placeholderValue) {
		super(PLACEHOLDER_ATTRIBUTE, placeholderValue);
	}
}
