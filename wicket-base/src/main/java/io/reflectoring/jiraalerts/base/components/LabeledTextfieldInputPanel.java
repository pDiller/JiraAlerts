package io.reflectoring.jiraalerts.base.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidator;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

public class LabeledTextfieldInputPanel extends GenericPanel<String> {

	private static final String CHANGE_EVENT = "change";
	private static final String CSS_CLASS_WARNING = "alert alert-warning";

	public LabeledTextfieldInputPanel(String id, IModel<String> textInputLabelModel, IModel<String> textInputModel,
	        IValidator<String>... validators) {
		super(id, textInputModel);
		setOutputMarkupId(true);

		Label textInputLabel = new Label("inputLabel", textInputLabelModel);

		TextField<String> textInputField = new TextField<>("input", getModel());
		textInputField.add(validators);

		FeedbackPanel textInputFeedbackPanel = new ComponentFeedbackPanel("feedback", textInputField) {

			@Override
			protected String getCSSClass(FeedbackMessage message) {
				return CSS_CLASS_WARNING;
			}
		};

		Form<String> textInputForm = new BootstrapForm<>("textInputForm");
		textInputForm.add(textInputLabel, textInputField, textInputFeedbackPanel);

		textInputField.add(new AjaxFormComponentUpdatingBehavior(CHANGE_EVENT) {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				rerenderTextInputPanel(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e) {
				rerenderTextInputPanel(target);
			}
		});

		add(textInputForm);

	}

	private void rerenderTextInputPanel(AjaxRequestTarget target) {
		if (target != null) {
			target.add(LabeledTextfieldInputPanel.this);
		}
	}
}
