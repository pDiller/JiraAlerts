package io.reflectoring.jiraalerts.common;

import java.util.Set;

import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;

public class FormComponentErrorClassModifier extends ClassAttributeModifier {

	private static final String INVALID_CLASS = "is-invalid";
	private static final String FORM_CONTROL_CLASS = "form-control";
	private boolean valid;

	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);

		if (!(component instanceof FormComponent)) {
			throw new IllegalArgumentException("Dieses Behavior darf ausschliesslich auf FeedbackPanels angewendet werden.");
		}

		valid = ((FormComponent) component).isValid();
	}

	@Override
	protected Set<String> update(Set<String> oldClasses) {
		oldClasses.clear();
		oldClasses.add(FORM_CONTROL_CLASS);

		if (!valid) {
			oldClasses.add(INVALID_CLASS);
		}

		return oldClasses;
	}
}
