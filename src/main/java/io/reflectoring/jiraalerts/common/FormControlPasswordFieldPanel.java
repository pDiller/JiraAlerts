package io.reflectoring.jiraalerts.common;

import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Standard implementation of {@link PasswordTextField} in JiraAlerts. This panel provides a {@link PasswordTextField} with following features:
 * <ol>
 * <li>labeled passwordField with placeholder</li>
 * <li>{@link FencedFeedbackPanel} for displaying errors</li>
 * <li>contains behavior for rendering css-classes on error ({@link FormComponentErrorClassModifier})</li>
 * </ol>
 */
public class FormControlPasswordFieldPanel extends GenericPanel<String> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param passwordModel
	 *            Model for valuebinding on the {@link PasswordTextField} of this panel.
	 * @param passwordLabelModel
	 *            The Label for the {@link PasswordTextField} of this panel.
	 */
	public FormControlPasswordFieldPanel(String id, IModel<String> passwordModel, IModel<String> passwordLabelModel) {
		super(id, passwordModel);

		PasswordTextField input = new PasswordTextField("input", getModel());
		input.setLabel(passwordLabelModel);
		input.add(new PlaceholderAttributeModifier(passwordLabelModel));
		input.add(new FormComponentErrorClassModifier());

		SimpleFormComponentLabel label = new SimpleFormComponentLabel("label", input);

		FeedbackPanel feedback = new FencedFeedbackPanel("feedback", input);
		feedback.add(new FeedbackPanelErrorClassModifier());

		add(input, label, feedback);
	}
}
