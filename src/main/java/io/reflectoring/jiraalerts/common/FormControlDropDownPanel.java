package io.reflectoring.jiraalerts.common;

import java.util.List;

import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Standard implementation of {@link DropDownChoice} in JiraAlerts. This panel provides a {@link DropDownChoice} with following features:
 * <ol>
 * <li>labeled drop-down with placeholder</li>
 * <li>{@link FencedFeedbackPanel} for displaying errors</li>
 * <li>contains behavior for rendering css-classes on error ({@link FormComponentErrorClassModifier})</li>
 * <li>required-state settable on constructor parameter (default is false)</li>
 * </ol>
 *
 * @param <T>
 *            The type of the modelobject of this class.
 */
public class FormControlDropDownPanel<T> extends GenericPanel<T> {

	private DropDownChoice<T> input;

	/**
	 * Constructor. {@link DropDownChoice} of this class is not required!
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param dropDownChoiceModel
	 *            Model for value binding on the {@link DropDownChoice} of this panel.
	 * @param dropDownListModel
	 *            Model for the list of choices on the {@link DropDownChoice} of this panel.
	 * @param choiceRenderer
	 *            The choiceRenderer that determines how to values of the {@link DropDownChoice} of this panel are displayed.
	 * @param dropDownChoiceLabelModel
	 *            The label for the {@link DropDownChoice} of this panel.
	 */
	public FormControlDropDownPanel(String id, IModel<T> dropDownChoiceModel, IModel<List<T>> dropDownListModel, IChoiceRenderer<T> choiceRenderer,
	        IModel<String> dropDownChoiceLabelModel) {
		this(id, dropDownChoiceModel, dropDownListModel, choiceRenderer, dropDownChoiceLabelModel, false);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param dropDownChoiceModel
	 *            Model for value binding on the {@link DropDownChoice} of this panel.
	 * @param dropDownListModel
	 *            Model for the list of choices on the {@link DropDownChoice} of this panel.
	 * @param dropDownChoiceLabelModel
	 *            The label for the {@link DropDownChoice} of this panel.
	 * @param choiceRenderer
	 *            The choiceRenderer that determines how to values of the {@link DropDownChoice} of this panel are displayed.
	 * @param dropDownRequired
	 *            Decides if the {@link DropDownChoice} of this panel is required.
	 */
	public FormControlDropDownPanel(String id, IModel<T> dropDownChoiceModel, IModel<List<T>> dropDownListModel, IChoiceRenderer<T> choiceRenderer,
	        IModel<String> dropDownChoiceLabelModel, boolean dropDownRequired) {
		super(id, dropDownChoiceModel);

		input = new DropDownChoice<>("input", getModel(), dropDownListModel, choiceRenderer);
		input.setLabel(dropDownChoiceLabelModel);
		input.setRequired(dropDownRequired);
		input.add(new PlaceholderAttributeModifier(dropDownChoiceLabelModel));
		input.add(new FormComponentErrorClassModifier());
		input.setNullValid(!dropDownRequired);

		SimpleFormComponentLabel label = new SimpleFormComponentLabel("label", input);

		FeedbackPanel feedback = new FencedFeedbackPanel("feedback", input);
		feedback.add(new FeedbackPanelErrorClassModifier());

		add(input, label, feedback);
	}

	public DropDownChoice<T> getInput() {
		return input;
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		input.detach();
	}
}
