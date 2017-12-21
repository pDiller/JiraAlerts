package io.reflectoring.jiraalerts.common;

import static java.util.Collections.emptySet;

import java.util.Set;

import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * {@link ClassAttributeModifier} for JiraAlerts FeedbackPanels. Sets the CSS classes for the {@link FeedbackPanel}, depending on whether there are
 * errors or not. Throws an {@link IllegalArgumentException} if this class is added to another component than a {@link FeedbackPanel}.
 */
public class FeedbackPanelErrorClassModifier extends ClassAttributeModifier {

	private static final String ALERT_CLASS = "alert";
	private static final String ALERT_DANGER_CLASS = "alert-danger";
	private boolean hasErrorMessages;

	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);

		if (!(component instanceof FeedbackPanel)) {
			throw new IllegalArgumentException("Dieses Behavior darf ausschliesslich auf FeedbackPanels angewendet werden.");
		}

		hasErrorMessages = ((FeedbackPanel) component).anyErrorMessage();
	}

	@Override
	protected Set<String> update(Set<String> oldClasses) {
		if (hasErrorMessages) {
			oldClasses.clear();
			oldClasses.add(ALERT_CLASS);
			oldClasses.add(ALERT_DANGER_CLASS);
			return oldClasses;
		} else {
			return emptySet();
		}
	}
}
