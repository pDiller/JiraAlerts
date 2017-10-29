package io.reflectoring.jiraalerts.base.components;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidator;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

public class LabeledTextfieldInputPanel extends GenericPanel<String> {

	private static final String CSS_CLASS_WARNING = "alert alert-warning";

	public LabeledTextfieldInputPanel(String id, IModel<String> textInputLabelModel, IModel<String> textInputModel,
	        IValidator<String>... validators) {
		this(id, textInputLabelModel, textInputModel, false, validators);
	}

	public LabeledTextfieldInputPanel(String id, IModel<String> textInputLabelModel, IModel<String> textInputModel, boolean inputRequired,
	        IValidator<String>... validators) {
		super(id, textInputModel);
		setOutputMarkupId(true);

		BootstrapForm<Void> textInputForm = new BootstrapForm<>("textInputForm");

		Label textInputLabel = new Label("inputLabel", textInputLabelModel);

		TextField<String> textInputField = new TextField<>("input", getModel());
		textInputField.setRequired(inputRequired);
		textInputField.add(validators);

		FeedbackPanel textInputFeedbackPanel = new FencedFeedbackPanel("feedback", textInputForm) {

			@Override
			protected String getCSSClass(FeedbackMessage message) {
				return CSS_CLASS_WARNING;
			}
		};

		textInputForm.add(textInputLabel, textInputField, textInputFeedbackPanel);
		add(textInputForm);
	}
}
