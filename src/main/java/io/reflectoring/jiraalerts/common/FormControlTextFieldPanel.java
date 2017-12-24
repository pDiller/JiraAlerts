package io.reflectoring.jiraalerts.common;

import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Standard implementation of {@link TextField} in JiraAlerts. This panel provides a {@link TextField} with following features:
 * <ol>
 * <li>labeled textfield with placeholder</li>
 * <li>{@link FencedFeedbackPanel} for displaying errors</li>
 * <li>contains behavior for rendering css-classes on error ({@link FormComponentErrorClassModifier})</li>
 * <li>required-state settable on constructor parameter (default is false)</li>
 * </ol>
 *
 * @param <T>
 *            The type of the modelobject of this class.
 */
public class FormControlTextFieldPanel<T> extends GenericPanel<T> {

	private TextField<T> input;

	/**
	 * Constructor. {@link TextField} of this class is not Required!
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param textfieldModel
	 *            Model for valuebinding on the {@link TextField} of this panel.
	 * @param textfieldLabelModel
	 *            The Label for the {@link TextField} of this panel.
	 */
	public FormControlTextFieldPanel(String id, IModel<T> textfieldModel, IModel<String> textfieldLabelModel) {
		this(id, textfieldModel, textfieldLabelModel, false);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param textfieldModel
	 *            Model for valuebinding on the {@link TextField} of this panel.
	 * @param textfieldLabelModel
	 *            The Label for the {@link TextField} of this panel.
	 * @param textfieldRequired
	 *            Decides if the {@link TextField} of this panel is required or not.
	 */
	public FormControlTextFieldPanel(String id, IModel<T> textfieldModel, IModel<String> textfieldLabelModel, boolean textfieldRequired) {
		super(id, textfieldModel);

		input = new TextField<>("input", getModel());
		input.setLabel(textfieldLabelModel);
		input.setRequired(textfieldRequired);
		input.add(new PlaceholderAttributeModifier(textfieldLabelModel));
		input.add(new FormComponentErrorClassModifier());

		SimpleFormComponentLabel label = new SimpleFormComponentLabel("label", input);

		FeedbackPanel feedback = new FencedFeedbackPanel("feedback", input);
		feedback.add(new FeedbackPanelErrorClassModifier());

		add(input, label, feedback);
	}

	public TextField<T> getInput() {
		return input;
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		input.detach();
	}
}
